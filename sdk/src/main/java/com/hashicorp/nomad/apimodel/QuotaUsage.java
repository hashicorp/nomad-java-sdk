package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class QuotaUsage extends ApiObject {
    private String name;
    private Map<String, QuotaLimit> used;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public QuotaUsage setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Used")
    public Map<String, QuotaLimit> getUsed() {
        return used;
    }

    public QuotaUsage setUsed(Map<String, QuotaLimit> used) {
        this.used = used;
        return this;
    }

    public QuotaUsage addUsed(String key, QuotaLimit value) {
        if (this.used == null)
            this.used = new java.util.HashMap<>();
        this.used.put(key, value);
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public QuotaUsage setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public QuotaUsage setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static QuotaUsage fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, QuotaUsage.class);
    }

    public static List<QuotaUsage> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, QuotaUsage.class);
    }
}
