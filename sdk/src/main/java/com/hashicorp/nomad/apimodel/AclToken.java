package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class AclToken extends ApiObject {
    private String accessorId;
    private String secretId;
    private String name;
    private String type;
    private List<String> policies;
    private boolean global;
    private Date createTime;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("AccessorID")
    public String getAccessorId() {
        return accessorId;
    }

    public AclToken setAccessorId(String accessorId) {
        this.accessorId = accessorId;
        return this;
    }

    @JsonProperty("SecretID")
    public String getSecretId() {
        return secretId;
    }

    public AclToken setSecretId(String secretId) {
        this.secretId = secretId;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public AclToken setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public AclToken setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Policies")
    public List<String> getPolicies() {
        return policies;
    }

    public AclToken setPolicies(List<String> policies) {
        this.policies = policies;
        return this;
    }

    public AclToken addPolicies(String... policies) {
        if (this.policies == null)
            this.policies = new java.util.ArrayList<>();
        for (String item : policies)
            this.policies.add(item);
        return this;
    }

    @JsonProperty("Global")
    public boolean getGlobal() {
        return global;
    }

    public AclToken setGlobal(boolean global) {
        this.global = global;
        return this;
    }

    @JsonProperty("CreateTime")
    public Date getCreateTime() {
        return createTime;
    }

    public AclToken setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public AclToken setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public AclToken setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AclToken fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AclToken.class);
    }

    public static List<AclToken> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AclToken.class);
    }
}
