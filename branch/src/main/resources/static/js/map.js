function debounce(func, wait, immediate) {
    let timeout;
    return function() {
        let context = this,
            args = arguments;
        let later = function() {
            timeout = null;
            if (!immediate) func.apply(context, args);
        };
        let callNow = immediate && !timeout;
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
        if (callNow) func.apply(context, args);
    };
}

function initAutocomplete() {
    let map = new google.maps.Map(document.getElementById('map'), {
        center: {
            lat: 1.3521,
            lng: 103.8198
        },
        zoom: 11,
        disableDefaultUI: true
    });

    // Create the search box and link it to the UI element.
    let inputContainer = document.querySelector('autocomplete-input-container');
    let autocomplete_results = document.querySelector('.autocomplete-results');
    let service = new google.maps.places.AutocompleteService();
    let serviceDetails = new google.maps.places.PlacesService(map);
    let marker = new google.maps.Marker({
        map: map
    });
    let displaySuggestions = function(predictions, status) {
        if (status != google.maps.places.PlacesServiceStatus.OK) {
            alert(status);
            return;
        }
        let results_html = [];
        predictions.forEach(function(prediction) {
            results_html.push(`<li class="autocomplete-item" data-type="place" data-place-id=${prediction.place_id}><span class="autocomplete-icon icon-localities"></span>      			    <span class="autocomplete-text">${prediction.description}</span></li>`);
        });
        autocomplete_results.innerHTML = results_html.join("");
        autocomplete_results.style.display = 'block';
        let autocomplete_items = autocomplete_results.querySelectorAll('.autocomplete-item');
        for (let autocomplete_item of autocomplete_items) {
            autocomplete_item.addEventListener('click', function() {
                let prediction = {};
                const selected_text = this.querySelector('.autocomplete-text').textContent;
                const place_id = this.getAttribute('data-place-id');
                let request = {
                    placeId: place_id,
                    fields: ['geometry', 'name']
                };

                serviceDetails.getDetails(request, function(place, status) {
                    if (status == google.maps.places.PlacesServiceStatus.OK) {
                        if (!place.geometry) {
                            console.log("Returned place contains no geometry");
                            return;
                        }
                        var bounds = new google.maps.LatLngBounds();
                        marker.setPosition(place.geometry.location);
                        if (place.geometry.viewport) {
                            bounds.union(place.geometry.viewport);
                        } else {
                            bounds.extend(place.geometry.location);
                        }
                        map.fitBounds(bounds);
                    }
                    autocomplete_input.value = selected_text;
                    autocomplete_results.style.display = 'none';
                });
            })
        }
    };
    let autocomplete_input = document.getElementById('my-input-autocomplete');
    autocomplete_input.addEventListener('input', debounce(function() {
        let value = this.value;
        value.replace('"', '\\"').replace(/^\s+|\s+$/g, '');
        if (value !== "") {
            service.getPlacePredictions({
                input: value
            }, displaySuggestions);
        } else {
            autocomplete_results.innerHTML = '';
            autocomplete_results.style.display = 'none';
        }
    }, 150));
}

document.addEventListener("DOMContentLoaded", function(event) {
    initAutocomplete();
});