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
public final class TaskArtifact {
    private String getterSource;
    private Map<String, String> getterOptions;
    private String relativeDest;

    @JsonProperty("GetterSource")
    public String getGetterSource() {
        return getterSource;
    }

    public TaskArtifact setGetterSource(String getterSource) {
        this.getterSource = getterSource;
        return this;
    }

    @JsonProperty("GetterOptions")
    public Map<String, String> getGetterOptions() {
        return getterOptions;
    }

    public TaskArtifact setGetterOptions(Map<String, String> getterOptions) {
        this.getterOptions = getterOptions;
        return this;
    }

    public TaskArtifact addGetterOptions(String key, String value) {
        if (this.getterOptions == null)
            this.getterOptions = new java.util.HashMap<>();
        this.getterOptions.put(key, value);
        return this;
    }

    @JsonProperty("RelativeDest")
    public String getRelativeDest() {
        return relativeDest;
    }

    public TaskArtifact setRelativeDest(String relativeDest) {
        this.relativeDest = relativeDest;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskArtifact fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskArtifact.class);
    }

    public static List<TaskArtifact> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskArtifact.class);
    }
}
