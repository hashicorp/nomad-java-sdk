package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class Constraint {
    private String lTarget;
    private String rTarget;
    private String operand;

    @JsonProperty("LTarget")
    public String getLTarget() {
        return lTarget;
    }

    public Constraint setLTarget(String lTarget) {
        this.lTarget = lTarget;
        return this;
    }

    @JsonProperty("RTarget")
    public String getRTarget() {
        return rTarget;
    }

    public Constraint setRTarget(String rTarget) {
        this.rTarget = rTarget;
        return this;
    }

    @JsonProperty("Operand")
    public String getOperand() {
        return operand;
    }

    public Constraint setOperand(String operand) {
        this.operand = operand;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Constraint fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Constraint.class);
    }

    public static List<Constraint> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Constraint.class);
    }
}
