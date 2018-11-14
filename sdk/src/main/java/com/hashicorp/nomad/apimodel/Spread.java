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
public final class Spread extends ApiObject {
    private String attribute;
    private int weight;
    private List<SpreadTarget> spreadTarget;

    @JsonProperty("Attribute")
    public String getAttribute() {
        return attribute;
    }

    public Spread setAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    @JsonProperty("Weight")
    public int getWeight() {
        return weight;
    }

    public Spread setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    @JsonProperty("SpreadTarget")
    public List<SpreadTarget> getSpreadTarget() {
        return spreadTarget;
    }

    public Spread setSpreadTarget(List<SpreadTarget> spreadTarget) {
        this.spreadTarget = spreadTarget;
        return this;
    }

    public Spread addSpreadTarget(SpreadTarget... spreadTarget) {
        if (this.spreadTarget == null)
            this.spreadTarget = new java.util.ArrayList<>();
        for (SpreadTarget item : spreadTarget)
            this.spreadTarget.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Spread fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Spread.class);
    }

    public static List<Spread> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Spread.class);
    }
}
