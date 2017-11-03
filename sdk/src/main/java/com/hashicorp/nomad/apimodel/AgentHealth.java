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
public final class AgentHealth extends ApiObject {
    private boolean ok;
    private String message;

    @JsonProperty("ok")
    public boolean getOk() {
        return ok;
    }

    public AgentHealth setOk(boolean ok) {
        this.ok = ok;
        return this;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public AgentHealth setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AgentHealth fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AgentHealth.class);
    }

    public static List<AgentHealth> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AgentHealth.class);
    }
}
