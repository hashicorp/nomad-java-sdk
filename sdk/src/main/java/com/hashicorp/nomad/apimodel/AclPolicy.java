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
public final class AclPolicy extends ApiObject {
    private String name;
    private String description;
    private String rules;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public AclPolicy setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    public AclPolicy setDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("Rules")
    public String getRules() {
        return rules;
    }

    public AclPolicy setRules(String rules) {
        this.rules = rules;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public AclPolicy setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public AclPolicy setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AclPolicy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AclPolicy.class);
    }

    public static List<AclPolicy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AclPolicy.class);
    }
}
