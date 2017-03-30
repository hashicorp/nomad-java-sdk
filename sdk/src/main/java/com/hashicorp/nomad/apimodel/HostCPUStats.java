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
public final class HostCPUStats {
    private String cpu;
    private double user;
    private double system;
    private double idle;

    @JsonProperty("CPU")
    public String getCpu() {
        return cpu;
    }

    public HostCPUStats setCpu(String cpu) {
        this.cpu = cpu;
        return this;
    }

    @JsonProperty("User")
    public double getUser() {
        return user;
    }

    public HostCPUStats setUser(double user) {
        this.user = user;
        return this;
    }

    @JsonProperty("System")
    public double getSystem() {
        return system;
    }

    public HostCPUStats setSystem(double system) {
        this.system = system;
        return this;
    }

    @JsonProperty("Idle")
    public double getIdle() {
        return idle;
    }

    public HostCPUStats setIdle(double idle) {
        this.idle = idle;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static HostCPUStats fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, HostCPUStats.class);
    }

    public static List<HostCPUStats> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, HostCPUStats.class);
    }
}
