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
public final class SpreadTarget extends ApiObject {
    private String value;
    private byte percent;

    @JsonProperty("Value")
    public String getValue() {
        return value;
    }

    public SpreadTarget setValue(String value) {
        this.value = value;
        return this;
    }

    @JsonProperty("Percent")
    public byte getPercent() {
        return percent;
    }

    public SpreadTarget setPercent(byte percent) {
        this.percent = percent;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static SpreadTarget fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, SpreadTarget.class);
    }

    public static List<SpreadTarget> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, SpreadTarget.class);
    }
}
