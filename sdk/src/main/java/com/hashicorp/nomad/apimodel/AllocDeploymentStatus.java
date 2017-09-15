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
public final class AllocDeploymentStatus extends ApiObject {
    private Boolean healthy;
    private BigInteger modifyIndex;

    @JsonProperty("Healthy")
    public Boolean getHealthy() {
        return healthy;
    }

    public AllocDeploymentStatus setHealthy(Boolean healthy) {
        this.healthy = healthy;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public AllocDeploymentStatus setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AllocDeploymentStatus fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AllocDeploymentStatus.class);
    }

    public static List<AllocDeploymentStatus> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AllocDeploymentStatus.class);
    }
}
