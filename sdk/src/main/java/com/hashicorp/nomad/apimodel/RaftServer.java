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
public final class RaftServer extends ApiObject {
    private String id;
    private String node;
    private String address;
    private boolean leader;
    private boolean voter;
    private String raftProtocol;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public RaftServer setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Node")
    public String getNode() {
        return node;
    }

    public RaftServer setNode(String node) {
        this.node = node;
        return this;
    }

    @JsonProperty("Address")
    public String getAddress() {
        return address;
    }

    public RaftServer setAddress(String address) {
        this.address = address;
        return this;
    }

    @JsonProperty("Leader")
    public boolean getLeader() {
        return leader;
    }

    public RaftServer setLeader(boolean leader) {
        this.leader = leader;
        return this;
    }

    @JsonProperty("Voter")
    public boolean getVoter() {
        return voter;
    }

    public RaftServer setVoter(boolean voter) {
        this.voter = voter;
        return this;
    }

    @JsonProperty("RaftProtocol")
    public String getRaftProtocol() {
        return raftProtocol;
    }

    public RaftServer setRaftProtocol(String raftProtocol) {
        this.raftProtocol = raftProtocol;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static RaftServer fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, RaftServer.class);
    }

    public static List<RaftServer> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, RaftServer.class);
    }
}
