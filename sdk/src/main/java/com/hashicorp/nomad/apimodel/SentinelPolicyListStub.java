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
public final class SentinelPolicyListStub extends ApiObject {
    private String name;
    private String description;
    private String scope;
    private String enforcementLevel;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public SentinelPolicyListStub setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    public SentinelPolicyListStub setDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("Scope")
    public String getScope() {
        return scope;
    }

    public SentinelPolicyListStub setScope(String scope) {
        this.scope = scope;
        return this;
    }

    @JsonProperty("EnforcementLevel")
    public String getEnforcementLevel() {
        return enforcementLevel;
    }

    public SentinelPolicyListStub setEnforcementLevel(String enforcementLevel) {
        this.enforcementLevel = enforcementLevel;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public SentinelPolicyListStub setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public SentinelPolicyListStub setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static SentinelPolicyListStub fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, SentinelPolicyListStub.class);
    }

    public static List<SentinelPolicyListStub> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, SentinelPolicyListStub.class);
    }
}
