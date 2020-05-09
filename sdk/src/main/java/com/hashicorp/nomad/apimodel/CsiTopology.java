package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class CsiTopology extends ApiObject {
    private Map<String, String> segments;

    @JsonProperty("Segments")
    public Map<String, String> getSegments() {
        return segments;
    }

    public CsiTopology setSegments(Map<String, String> segments) {
        this.segments = segments;
        return this;
    }

    public CsiTopology addSegments(String key, String value) {
        if (this.segments == null)
            this.segments = new java.util.HashMap<>();
        this.segments.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static CsiTopology fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CsiTopology.class);
    }

    public static List<CsiTopology> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CsiTopology.class);
    }
}
