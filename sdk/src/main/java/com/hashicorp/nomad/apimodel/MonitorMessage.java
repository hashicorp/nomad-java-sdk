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
public final class MonitorMessage extends ApiObject {
    private Integer level;
    private String message;

    @JsonProperty("Level")
    public Integer getLevel() {
        return level;
    }

    public MonitorMessage setLevel(Integer level) {
        this.level = level;
        return this;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    public MonitorMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static MonitorMessage fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, MonitorMessage.class);
    }

    public static List<MonitorMessage> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, MonitorMessage.class);
    }
}
