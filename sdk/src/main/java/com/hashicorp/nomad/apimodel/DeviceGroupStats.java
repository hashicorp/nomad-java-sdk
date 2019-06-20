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
public final class DeviceGroupStats extends ApiObject {
    private String vendor;
    private String type;
    private String name;
    private Map<String, DeviceStats> instanceStats;

    @JsonProperty("Vendor")
    public String getVendor() {
        return vendor;
    }

    public DeviceGroupStats setVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public DeviceGroupStats setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public DeviceGroupStats setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("InstanceStats")
    public Map<String, DeviceStats> getInstanceStats() {
        return instanceStats;
    }

    public DeviceGroupStats setInstanceStats(Map<String, DeviceStats> instanceStats) {
        this.instanceStats = instanceStats;
        return this;
    }

    public DeviceGroupStats addInstanceStats(String key, DeviceStats value) {
        if (this.instanceStats == null)
            this.instanceStats = new java.util.HashMap<>();
        this.instanceStats.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DeviceGroupStats fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DeviceGroupStats.class);
    }

    public static List<DeviceGroupStats> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DeviceGroupStats.class);
    }
}
