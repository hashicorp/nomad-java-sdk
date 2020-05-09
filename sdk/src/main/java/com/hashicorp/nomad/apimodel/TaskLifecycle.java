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
public final class TaskLifecycle extends ApiObject {
    private String hook;
    private boolean sidecar;

    @JsonProperty("Hook")
    public String getHook() {
        return hook;
    }

    public TaskLifecycle setHook(String hook) {
        this.hook = hook;
        return this;
    }

    @JsonProperty("Sidecar")
    public boolean getSidecar() {
        return sidecar;
    }

    public TaskLifecycle setSidecar(boolean sidecar) {
        this.sidecar = sidecar;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskLifecycle fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskLifecycle.class);
    }

    public static List<TaskLifecycle> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskLifecycle.class);
    }
}
