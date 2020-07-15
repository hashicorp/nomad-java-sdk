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
public final class MultiregionStrategy extends ApiObject {
    private Integer maxParallel;
    private String onFailure;

    @JsonProperty("MaxParallel")
    public Integer getMaxParallel() {
        return maxParallel;
    }

    public MultiregionStrategy setMaxParallel(Integer maxParallel) {
        this.maxParallel = maxParallel;
        return this;
    }

    @JsonProperty("OnFailure")
    public String getOnFailure() {
        return onFailure;
    }

    public MultiregionStrategy setOnFailure(String onFailure) {
        this.onFailure = onFailure;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static MultiregionStrategy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, MultiregionStrategy.class);
    }

    public static List<MultiregionStrategy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, MultiregionStrategy.class);
    }
}
