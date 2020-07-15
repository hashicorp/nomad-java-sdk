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
public final class Multiregion extends ApiObject {
    private MultiregionStrategy strategy;
    private List<MultiregionRegion> regions;

    @JsonProperty("Strategy")
    public MultiregionStrategy getStrategy() {
        return strategy;
    }

    public Multiregion setStrategy(MultiregionStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    @JsonProperty("Regions")
    public List<MultiregionRegion> getRegions() {
        return regions;
    }

    public Multiregion setRegions(List<MultiregionRegion> regions) {
        this.regions = regions;
        return this;
    }

    public Multiregion addRegions(MultiregionRegion... regions) {
        if (this.regions == null)
            this.regions = new java.util.ArrayList<>();
        for (MultiregionRegion item : regions)
            this.regions.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Multiregion fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Multiregion.class);
    }

    public static List<Multiregion> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Multiregion.class);
    }
}
