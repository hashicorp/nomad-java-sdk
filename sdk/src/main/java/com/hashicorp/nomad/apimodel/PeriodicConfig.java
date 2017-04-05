package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class PeriodicConfig extends ApiObject {
    private Boolean enabled;
    private String spec;
    private String specType;
    private Boolean prohibitOverlap;
    private String timeZone;

    @JsonProperty("Enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public PeriodicConfig setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @JsonProperty("Spec")
    public String getSpec() {
        return spec;
    }

    public PeriodicConfig setSpec(String spec) {
        this.spec = spec;
        return this;
    }

    @JsonProperty("SpecType")
    public String getSpecType() {
        return specType;
    }

    public PeriodicConfig setSpecType(String specType) {
        this.specType = specType;
        return this;
    }

    @JsonProperty("ProhibitOverlap")
    public Boolean getProhibitOverlap() {
        return prohibitOverlap;
    }

    public PeriodicConfig setProhibitOverlap(Boolean prohibitOverlap) {
        this.prohibitOverlap = prohibitOverlap;
        return this;
    }

    @JsonProperty("TimeZone")
    public String getTimeZone() {
        return timeZone;
    }

    public PeriodicConfig setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static PeriodicConfig fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, PeriodicConfig.class);
    }

    public static List<PeriodicConfig> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, PeriodicConfig.class);
    }
}
