package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class DriverInfo extends ApiObject {
    private Map<String, String> attributes;
    private boolean detected;
    private boolean healthy;
    private String healthDescription;
    private Date updateTime;

    @JsonProperty("Attributes")
    public Map<String, String> getAttributes() {
        return attributes;
    }

    public DriverInfo setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
        return this;
    }

    public DriverInfo addAttributes(String key, String value) {
        if (this.attributes == null)
            this.attributes = new java.util.HashMap<>();
        this.attributes.put(key, value);
        return this;
    }

    @JsonProperty("Detected")
    public boolean getDetected() {
        return detected;
    }

    public DriverInfo setDetected(boolean detected) {
        this.detected = detected;
        return this;
    }

    @JsonProperty("Healthy")
    public boolean getHealthy() {
        return healthy;
    }

    public DriverInfo setHealthy(boolean healthy) {
        this.healthy = healthy;
        return this;
    }

    @JsonProperty("HealthDescription")
    public String getHealthDescription() {
        return healthDescription;
    }

    public DriverInfo setHealthDescription(String healthDescription) {
        this.healthDescription = healthDescription;
        return this;
    }

    @JsonProperty("UpdateTime")
    public Date getUpdateTime() {
        return updateTime;
    }

    public DriverInfo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DriverInfo fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DriverInfo.class);
    }

    public static List<DriverInfo> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DriverInfo.class);
    }
}
