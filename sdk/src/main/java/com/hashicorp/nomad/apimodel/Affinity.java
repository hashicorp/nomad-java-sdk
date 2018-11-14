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
public final class Affinity extends ApiObject {
    private String lTarget;
    private String rTarget;
    private String operand;
    private double weight;

    @JsonProperty("LTarget")
    public String getLTarget() {
        return lTarget;
    }

    public Affinity setLTarget(String lTarget) {
        this.lTarget = lTarget;
        return this;
    }

    @JsonProperty("RTarget")
    public String getRTarget() {
        return rTarget;
    }

    public Affinity setRTarget(String rTarget) {
        this.rTarget = rTarget;
        return this;
    }

    @JsonProperty("Operand")
    public String getOperand() {
        return operand;
    }

    public Affinity setOperand(String operand) {
        this.operand = operand;
        return this;
    }

    @JsonProperty("Weight")
    public double getWeight() {
        return weight;
    }

    public Affinity setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Affinity fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Affinity.class);
    }

    public static List<Affinity> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Affinity.class);
    }
}
