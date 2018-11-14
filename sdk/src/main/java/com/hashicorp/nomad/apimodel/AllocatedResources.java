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
public final class AllocatedResources extends ApiObject {
    private Map<String, AllocatedTaskResources> tasks;
    private AllocatedSharedResources shared;

    @JsonProperty("Tasks")
    public Map<String, AllocatedTaskResources> getTasks() {
        return tasks;
    }

    public AllocatedResources setTasks(Map<String, AllocatedTaskResources> tasks) {
        this.tasks = tasks;
        return this;
    }

    public AllocatedResources addTasks(String key, AllocatedTaskResources value) {
        if (this.tasks == null)
            this.tasks = new java.util.HashMap<>();
        this.tasks.put(key, value);
        return this;
    }

    @JsonProperty("Shared")
    public AllocatedSharedResources getShared() {
        return shared;
    }

    public AllocatedResources setShared(AllocatedSharedResources shared) {
        this.shared = shared;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AllocatedResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AllocatedResources.class);
    }

    public static List<AllocatedResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AllocatedResources.class);
    }
}
