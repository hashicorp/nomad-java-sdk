package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class QuotaSpec extends ApiObject {
    private String name;
    private String description;
    private List<QuotaLimit> limits;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public QuotaSpec setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    public QuotaSpec setDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("Limits")
    public List<QuotaLimit> getLimits() {
        return limits;
    }

    public QuotaSpec setLimits(List<QuotaLimit> limits) {
        this.limits = limits;
        return this;
    }

    public QuotaSpec addLimits(QuotaLimit... limits) {
        if (this.limits == null)
            this.limits = new java.util.ArrayList<>();
        for (QuotaLimit item : limits)
            this.limits.add(item);
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public QuotaSpec setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public QuotaSpec setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static QuotaSpec fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, QuotaSpec.class);
    }

    public static List<QuotaSpec> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, QuotaSpec.class);
    }
}
