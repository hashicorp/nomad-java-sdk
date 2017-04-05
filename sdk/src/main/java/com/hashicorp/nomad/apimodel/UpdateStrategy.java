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
public final class UpdateStrategy extends ApiObject {
    private long stagger;
    private int maxParallel;

    @JsonProperty("Stagger")
    public long getStagger() {
        return stagger;
    }

    public UpdateStrategy setStagger(long stagger) {
        this.stagger = stagger;
        return this;
    }

    @JsonProperty("MaxParallel")
    public int getMaxParallel() {
        return maxParallel;
    }

    public UpdateStrategy setMaxParallel(int maxParallel) {
        this.maxParallel = maxParallel;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static UpdateStrategy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, UpdateStrategy.class);
    }

    public static List<UpdateStrategy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, UpdateStrategy.class);
    }
}
