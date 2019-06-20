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
public final class StatObject extends ApiObject {
    private Map<String, StatObject> nested;
    private Map<String, StatValue> attributes;

    @JsonProperty("Nested")
    public Map<String, StatObject> getNested() {
        return nested;
    }

    public StatObject setNested(Map<String, StatObject> nested) {
        this.nested = nested;
        return this;
    }

    public StatObject addNested(String key, StatObject value) {
        if (this.nested == null)
            this.nested = new java.util.HashMap<>();
        this.nested.put(key, value);
        return this;
    }

    @JsonProperty("Attributes")
    public Map<String, StatValue> getAttributes() {
        return attributes;
    }

    public StatObject setAttributes(Map<String, StatValue> attributes) {
        this.attributes = attributes;
        return this;
    }

    public StatObject addAttributes(String key, StatValue value) {
        if (this.attributes == null)
            this.attributes = new java.util.HashMap<>();
        this.attributes.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static StatObject fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, StatObject.class);
    }

    public static List<StatObject> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, StatObject.class);
    }
}
