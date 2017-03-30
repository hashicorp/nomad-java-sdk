package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class TaskResourceUsage {
    private ResourceUsage resourceUsage;
    private long timestamp;
    private Map<String, ResourceUsage> pids;

    @JsonProperty("ResourceUsage")
    public ResourceUsage getResourceUsage() {
        return resourceUsage;
    }

    public TaskResourceUsage setResourceUsage(ResourceUsage resourceUsage) {
        this.resourceUsage = resourceUsage;
        return this;
    }

    @JsonProperty("Timestamp")
    public long getTimestamp() {
        return timestamp;
    }

    public TaskResourceUsage setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @JsonProperty("Pids")
    public Map<String, ResourceUsage> getPids() {
        return pids;
    }

    public TaskResourceUsage setPids(Map<String, ResourceUsage> pids) {
        this.pids = pids;
        return this;
    }

    public TaskResourceUsage addPids(String key, ResourceUsage value) {
        if (this.pids == null)
            this.pids = new java.util.HashMap<>();
        this.pids.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskResourceUsage fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskResourceUsage.class);
    }

    public static List<TaskResourceUsage> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskResourceUsage.class);
    }
}
