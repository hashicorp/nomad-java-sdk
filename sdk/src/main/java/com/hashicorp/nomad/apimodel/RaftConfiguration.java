package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class RaftConfiguration extends ApiObject {
    private List<RaftServer> servers;
    private BigInteger index;

    @JsonProperty("Servers")
    public List<RaftServer> getServers() {
        return servers;
    }

    public RaftConfiguration setServers(List<RaftServer> servers) {
        this.servers = servers;
        return this;
    }

    public RaftConfiguration addServers(RaftServer... servers) {
        if (this.servers == null)
            this.servers = new java.util.ArrayList<>();
        for (RaftServer item : servers)
            this.servers.add(item);
        return this;
    }

    @JsonProperty("Index")
    public BigInteger getIndex() {
        return index;
    }

    public RaftConfiguration setIndex(BigInteger index) {
        this.index = index;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static RaftConfiguration fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, RaftConfiguration.class);
    }

    public static List<RaftConfiguration> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, RaftConfiguration.class);
    }
}
