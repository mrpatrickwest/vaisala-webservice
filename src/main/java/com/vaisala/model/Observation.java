package com.vaisala.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by pwest on 12/09/16.
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("observation")
public class Observation {

    @JsonProperty("id")
    @SerializedName("id")
    private int id;
    @JsonProperty("observed_timestamp")
    @SerializedName("observed_timestamp")
    private Date observedTimestamp;
    @JsonProperty("received_timestamp")
    @SerializedName("received_timestamp")
    private Date receivedTimestamp;
    @JsonProperty("value")
    @SerializedName("value")
    private float value;
    @JsonProperty("quality_code")
    @SerializedName("quality_code")
    private int qualityCode;
    @JsonProperty("quality")
    @SerializedName("quality")
    private String quality;
    @JsonProperty("sensor_id")
    @SerializedName("sensor_id")
    private int sensorId;

    public Observation() {}

    public Observation(final int id, final Date current, final Date received, final float value, final int qualityCode, final int sensor) {
        this.id = id;
        this.observedTimestamp = current;
        this.receivedTimestamp = received;
        this.value = value;
        this.qualityCode = qualityCode;
        if(qualityCode == 0) quality = "Good";
        else if(qualityCode == 1) this.quality = "Arrived Late";
        else if(qualityCode == 2) this.quality = "Out of Range";
        else this.quality = "Unknown";
        this.sensorId = sensor;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Date getObservedTimestamp() {
        return observedTimestamp;
    }

    public void setObservedTimestamp(final Date observedTimestamp) {
        this.observedTimestamp = observedTimestamp;
    }

    public Date getReceivedTimestamp() {
        return receivedTimestamp;
    }

    public void setReceivedTimestamp(final Date receivedTimestamp) {
        this.receivedTimestamp = receivedTimestamp;
    }

    public float getValue() {
        return value;
    }

    public void setValue(final float value) {
        this.value = value;
    }

    public int getQualityCode() {
        return qualityCode;
    }

    public void setQualityCode(final int qualityCode) {
        this.qualityCode = qualityCode;
        if(qualityCode == 0) quality = "Good";
        else if(qualityCode == 1) this.quality = "Arrived Late";
        else if(qualityCode == 2) this.quality = "Out of Range";
        else this.quality = "Unknown";
    }

    public String getQuality() {
        return this.quality;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(final int sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Observation observation = (Observation) o;
        return this.id == observation.getId();
    }

    @Override
    public String toString() {
        return "Observation{" +
                "id=" + id +
               "observed_timestamp=" + observedTimestamp +
               "received_timestamp=" + receivedTimestamp +
               "value=" + value +
               "quality=" + quality +
               "sensor_id=" + sensorId +
                '}';
    }
}

