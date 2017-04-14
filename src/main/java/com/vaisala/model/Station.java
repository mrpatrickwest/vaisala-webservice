package com.vaisala.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pwest on 12/09/16.
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("station")
public class Station {

    @JsonProperty("id")
    @SerializedName("id")
    private int id;
    @JsonProperty("name")
    @SerializedName("name")
    private String name;
    @JsonProperty("location")
    @SerializedName("location")
    private String location;

    public Station() {}

    public Station(final int id, final String name, final String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;
        return this.id == station.getId();
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
               "name=" + name +
               "location=" + location +
                '}';
    }
}

