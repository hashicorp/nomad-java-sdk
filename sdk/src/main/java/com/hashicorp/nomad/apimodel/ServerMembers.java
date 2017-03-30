package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class ServerMembers {
    private String serverName;
    private String serverRegion;
    private String serverDc;
    private List<AgentMember> members;

    @JsonProperty("ServerName")
    public String getServerName() {
        return serverName;
    }

    public ServerMembers setServerName(String serverName) {
        this.serverName = serverName;
        return this;
    }

    @JsonProperty("ServerRegion")
    public String getServerRegion() {
        return serverRegion;
    }

    public ServerMembers setServerRegion(String serverRegion) {
        this.serverRegion = serverRegion;
        return this;
    }

    @JsonProperty("ServerDC")
    public String getServerDc() {
        return serverDc;
    }

    public ServerMembers setServerDc(String serverDc) {
        this.serverDc = serverDc;
        return this;
    }

    @JsonProperty("Members")
    public List<AgentMember> getMembers() {
        return members;
    }

    public ServerMembers setMembers(List<AgentMember> members) {
        this.members = members;
        return this;
    }

    public ServerMembers addMembers(AgentMember... members) {
        if (this.members == null)
            this.members = new java.util.ArrayList<>();
        for (AgentMember item : members)
            this.members.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ServerMembers fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ServerMembers.class);
    }

    public static List<ServerMembers> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ServerMembers.class);
    }
}
