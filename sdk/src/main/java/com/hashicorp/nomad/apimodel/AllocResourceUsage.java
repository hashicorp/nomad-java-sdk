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
public final class AllocResourceUsage {
    private ResourceUsage resourceUsage;
    private Map<String, TaskResourceUsage> tasks;
    private long timestamp;

    @JsonProperty("ResourceUsage")
    public ResourceUsage getResourceUsage() {
        return resourceUsage;
    }

    public AllocResourceUsage setResourceUsage(ResourceUsage resourceUsage) {
        this.resourceUsage = resourceUsage;
        return this;
    }

    @JsonProperty("Tasks")
    public Map<String, TaskResourceUsage> getTasks() {
        return tasks;
    }

    public AllocResourceUsage setTasks(Map<String, TaskResourceUsage> tasks) {
        this.tasks = tasks;
        return this;
    }

    public AllocResourceUsage addTasks(String key, TaskResourceUsage value) {
        if (this.tasks == null)
            this.tasks = new java.util.HashMap<>();
        this.tasks.put(key, value);
        return this;
    }

    @JsonProperty("Timestamp")
    public long getTimestamp() {
        return timestamp;
    }

    public AllocResourceUsage setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AllocResourceUsage fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AllocResourceUsage.class);
    }

    public static List<AllocResourceUsage> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AllocResourceUsage.class);
    }
}
