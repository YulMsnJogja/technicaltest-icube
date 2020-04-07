package com.icube.branch.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (value != null){
            String formattedDate = dateFormat.format(value);
            jgen.writeString(formattedDate);
        }
    }
}

