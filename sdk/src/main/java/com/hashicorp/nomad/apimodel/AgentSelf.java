package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class AgentSelf extends ApiObject {
    private Map<String, Object> config;
    private AgentMember member;
    private Map<String, Map<String, String>> stats;

    @JsonProperty("config")
    public Map<String, Object> getConfig() {
        return config;
    }

    public AgentSelf setConfig(Map<String, Object> config) {
        this.config = config;
        return this;
    }

    public AgentSelf addConfig(String key, Object value) {
        if (this.config == null)
            this.config = new java.util.HashMap<>();
        this.config.put(key, value);
        return this;
    }

    @JsonProperty("member")
    public AgentMember getMember() {
        return member;
    }

    public AgentSelf setMember(AgentMember member) {
        this.member = member;
        return this;
    }

    @JsonProperty("stats")
    public Map<String, Map<String, String>> getStats() {
        return stats;
    }

    public AgentSelf setStats(Map<String, Map<String, String>> stats) {
        this.stats = stats;
        return this;
    }

    public AgentSelf addStats(String key, Map<String, String> value) {
        if (this.stats == null)
            this.stats = new java.util.HashMap<>();
        this.stats.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AgentSelf fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AgentSelf.class);
    }

    public static List<AgentSelf> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AgentSelf.class);
    }
}
