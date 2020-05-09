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
public final class ConsulConnect extends ApiObject {
    private boolean Native;
    private ConsulSidecarService sidecarService;
    private SidecarTask sidecarTask;

    @JsonProperty("Native")
    public boolean getNative() {
        return Native;
    }

    public ConsulConnect setNative(boolean Native) {
        this.Native = Native;
        return this;
    }

    @JsonProperty("SidecarService")
    public ConsulSidecarService getSidecarService() {
        return sidecarService;
    }

    public ConsulConnect setSidecarService(ConsulSidecarService sidecarService) {
        this.sidecarService = sidecarService;
        return this;
    }

    @JsonProperty("SidecarTask")
    public SidecarTask getSidecarTask() {
        return sidecarTask;
    }

    public ConsulConnect setSidecarTask(SidecarTask sidecarTask) {
        this.sidecarTask = sidecarTask;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ConsulConnect fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ConsulConnect.class);
    }

    public static List<ConsulConnect> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ConsulConnect.class);
    }
}
