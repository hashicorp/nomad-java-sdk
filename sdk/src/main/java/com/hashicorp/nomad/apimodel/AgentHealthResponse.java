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
public final class AgentHealthResponse extends ApiObject {
    private AgentHealth client;
    private AgentHealth server;

    @JsonProperty("client")
    public AgentHealth getClient() {
        return client;
    }

    public AgentHealthResponse setClient(AgentHealth client) {
        this.client = client;
        return this;
    }

    @JsonProperty("server")
    public AgentHealth getServer() {
        return server;
    }

    public AgentHealthResponse setServer(AgentHealth server) {
        this.server = server;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AgentHealthResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AgentHealthResponse.class);
    }

    public static List<AgentHealthResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AgentHealthResponse.class);
    }
}
