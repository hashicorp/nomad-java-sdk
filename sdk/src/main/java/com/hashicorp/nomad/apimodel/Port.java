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
public final class Port extends ApiObject {
    private String label;
    private int value;
    private int to;

    @JsonProperty("Label")
    public String getLabel() {
        return label;
    }

    public Port setLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("Value")
    public int getValue() {
        return value;
    }

    public Port setValue(int value) {
        this.value = value;
        return this;
    }

    @JsonProperty("To")
    public int getTo() {
        return to;
    }

    public Port setTo(int to) {
        this.to = to;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Port fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Port.class);
    }

    public static List<Port> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Port.class);
    }
}
