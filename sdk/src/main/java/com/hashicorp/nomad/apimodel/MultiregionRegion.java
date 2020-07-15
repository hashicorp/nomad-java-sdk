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
public final class MultiregionRegion extends ApiObject {
    private String name;
    private Integer count;
    private List<String> datacenters;
    private Map<String, String> meta;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public MultiregionRegion setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Count")
    public Integer getCount() {
        return count;
    }

    public MultiregionRegion setCount(Integer count) {
        this.count = count;
        return this;
    }

    @JsonProperty("Datacenters")
    public List<String> getDatacenters() {
        return datacenters;
    }

    public MultiregionRegion setDatacenters(List<String> datacenters) {
        this.datacenters = datacenters;
        return this;
    }

    public MultiregionRegion addDatacenters(String... datacenters) {
        if (this.datacenters == null)
            this.datacenters = new java.util.ArrayList<>();
        for (String item : datacenters)
            this.datacenters.add(item);
        return this;
    }

    @JsonProperty("Meta")
    public Map<String, String> getMeta() {
        return meta;
    }

    public MultiregionRegion setMeta(Map<String, String> meta) {
        this.meta = meta;
        return this;
    }

    public MultiregionRegion addMeta(String key, String value) {
        if (this.meta == null)
            this.meta = new java.util.HashMap<>();
        this.meta.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static MultiregionRegion fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, MultiregionRegion.class);
    }

    public static List<MultiregionRegion> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, MultiregionRegion.class);
    }
}
