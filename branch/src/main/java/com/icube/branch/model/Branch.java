package com.icube.branch.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.icube.branch.helper.DateSerializer;
import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "branch")
@SQLDelete(sql = "UPDATE branch SET deleted=true WHERE id=?")
@Where(clause = "is_deleted = true")
public class Branch {

    @Id
    @Column(name = "branch_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;

    @Column(name = "operating_hours")
    private String operatingHours;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @JsonSerialize(using = DateSerializer.class)
    @Column(name = "create_on")
    private Date createOn;

    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
