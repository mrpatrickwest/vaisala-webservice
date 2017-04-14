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
@JsonRootName("sensor")
public class Sensor {

    @JsonProperty("id")
    @SerializedName("id")
    private int id;
    @JsonProperty("name")
    @SerializedName("name")
    private String name;
    @JsonProperty("type")
    @SerializedName("type")
    private String type;
    @JsonProperty("station_id")
    @SerializedName("station_id")
    private int stationId;

    public Sensor() {}

    public Sensor(final int id, final String name, final String type, final int station) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stationId = station;
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

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(final int stationId) {
        this.stationId = stationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;
        return this.id == sensor.getId();
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
               "name=" + name +
               "type=" + type +
               "station_id=" + stationId +
                '}';
    }
}

