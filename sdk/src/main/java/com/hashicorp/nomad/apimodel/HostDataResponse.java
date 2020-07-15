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
public final class HostDataResponse extends ApiObject {
    private String agentId;
    private HostData hostData;

    @JsonProperty("AgentID")
    public String getAgentId() {
        return agentId;
    }

    public HostDataResponse setAgentId(String agentId) {
        this.agentId = agentId;
        return this;
    }

    @JsonProperty("HostData")
    public HostData getHostData() {
        return hostData;
    }

    public HostDataResponse setHostData(HostData hostData) {
        this.hostData = hostData;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static HostDataResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, HostDataResponse.class);
    }

    public static List<HostDataResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, HostDataResponse.class);
    }
}
