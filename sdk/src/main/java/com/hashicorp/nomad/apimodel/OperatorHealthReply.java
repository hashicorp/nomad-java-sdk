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
public final class OperatorHealthReply extends ApiObject {
    private boolean healthy;
    private int failureTolerance;
    private List<ServerHealth> servers;

    @JsonProperty("Healthy")
    public boolean getHealthy() {
        return healthy;
    }

    public OperatorHealthReply setHealthy(boolean healthy) {
        this.healthy = healthy;
        return this;
    }

    @JsonProperty("FailureTolerance")
    public int getFailureTolerance() {
        return failureTolerance;
    }

    public OperatorHealthReply setFailureTolerance(int failureTolerance) {
        this.failureTolerance = failureTolerance;
        return this;
    }

    @JsonProperty("Servers")
    public List<ServerHealth> getServers() {
        return servers;
    }

    public OperatorHealthReply setServers(List<ServerHealth> servers) {
        this.servers = servers;
        return this;
    }

    public OperatorHealthReply addServers(ServerHealth... servers) {
        if (this.servers == null)
            this.servers = new java.util.ArrayList<>();
        for (ServerHealth item : servers)
            this.servers.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static OperatorHealthReply fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, OperatorHealthReply.class);
    }

    public static List<OperatorHealthReply> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, OperatorHealthReply.class);
    }
}
