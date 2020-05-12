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
public final class TaskCsiPluginConfig extends ApiObject {
    private String id;
    private String type;
    private String mountDir;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public TaskCsiPluginConfig setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public TaskCsiPluginConfig setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("MountDir")
    public String getMountDir() {
        return mountDir;
    }

    public TaskCsiPluginConfig setMountDir(String mountDir) {
        this.mountDir = mountDir;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskCsiPluginConfig fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskCsiPluginConfig.class);
    }

    public static List<TaskCsiPluginConfig> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskCsiPluginConfig.class);
    }
}
