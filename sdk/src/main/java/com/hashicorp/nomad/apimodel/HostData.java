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
public final class HostData extends ApiObject {
    private String os;
    private List<Map<String, String>> network;
    private String resolvConf;
    private String hosts;
    private Map<String, String> environment;
    private Map<String, DiskUsage> disk;

    @JsonProperty("OS")
    public String getOs() {
        return os;
    }

    public HostData setOs(String os) {
        this.os = os;
        return this;
    }

    @JsonProperty("Network")
    public List<Map<String, String>> getNetwork() {
        return network;
    }

    public HostData setNetwork(List<Map<String, String>> network) {
        this.network = network;
        return this;
    }

    public HostData addNetwork(Map<String, String>... network) {
        if (this.network == null)
            this.network = new java.util.ArrayList<>();
        for (Map<String, String> item : network)
            this.network.add(item);
        return this;
    }

    @JsonProperty("ResolvConf")
    public String getResolvConf() {
        return resolvConf;
    }

    public HostData setResolvConf(String resolvConf) {
        this.resolvConf = resolvConf;
        return this;
    }

    @JsonProperty("Hosts")
    public String getHosts() {
        return hosts;
    }

    public HostData setHosts(String hosts) {
        this.hosts = hosts;
        return this;
    }

    @JsonProperty("Environment")
    public Map<String, String> getEnvironment() {
        return environment;
    }

    public HostData setEnvironment(Map<String, String> environment) {
        this.environment = environment;
        return this;
    }

    public HostData addEnvironment(String key, String value) {
        if (this.environment == null)
            this.environment = new java.util.HashMap<>();
        this.environment.put(key, value);
        return this;
    }

    @JsonProperty("Disk")
    public Map<String, DiskUsage> getDisk() {
        return disk;
    }

    public HostData setDisk(Map<String, DiskUsage> disk) {
        this.disk = disk;
        return this;
    }

    public HostData addDisk(String key, DiskUsage value) {
        if (this.disk == null)
            this.disk = new java.util.HashMap<>();
        this.disk.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static HostData fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, HostData.class);
    }

    public static List<HostData> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, HostData.class);
    }
}
