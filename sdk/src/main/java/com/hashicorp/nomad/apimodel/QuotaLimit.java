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
public final class QuotaLimit extends ApiObject {
    private String region;
    private Resources regionLimit;
    private byte[] hash;

    @JsonProperty("Region")
    public String getRegion() {
        return region;
    }

    public QuotaLimit setRegion(String region) {
        this.region = region;
        return this;
    }

    @JsonProperty("RegionLimit")
    public Resources getRegionLimit() {
        return regionLimit;
    }

    public QuotaLimit setRegionLimit(Resources regionLimit) {
        this.regionLimit = regionLimit;
        return this;
    }

    @JsonProperty("Hash")
    public byte[] getHash() {
        return hash;
    }

    public QuotaLimit setHash(byte[] hash) {
        this.hash = hash;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static QuotaLimit fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, QuotaLimit.class);
    }

    public static List<QuotaLimit> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, QuotaLimit.class);
    }
}
