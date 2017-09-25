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
public final class SentinelPolicy extends ApiObject {
    private String name;
    private String description;
    private String scope;
    private String enforcementLevel;
    private String policy;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public SentinelPolicy setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    public SentinelPolicy setDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("Scope")
    public String getScope() {
        return scope;
    }

    public SentinelPolicy setScope(String scope) {
        this.scope = scope;
        return this;
    }

    @JsonProperty("EnforcementLevel")
    public String getEnforcementLevel() {
        return enforcementLevel;
    }

    public SentinelPolicy setEnforcementLevel(String enforcementLevel) {
        this.enforcementLevel = enforcementLevel;
        return this;
    }

    @JsonProperty("Policy")
    public String getPolicy() {
        return policy;
    }

    public SentinelPolicy setPolicy(String policy) {
        this.policy = policy;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public SentinelPolicy setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public SentinelPolicy setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static SentinelPolicy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, SentinelPolicy.class);
    }

    public static List<SentinelPolicy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, SentinelPolicy.class);
    }
}
