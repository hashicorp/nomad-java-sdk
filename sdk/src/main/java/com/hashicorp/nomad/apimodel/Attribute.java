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
public final class Attribute extends ApiObject {
    /**
     * The generator is going to make a mess of these, because the JSON names collide with Java types.
     * This file will need to be manually restored.
     */
    private double floatVal;
    private Long intVal;
    private String stringVal;
    private Boolean boolVal;
    private String unit;

    @JsonProperty("Float")
    public double getFloat() {
        return floatVal;
    }

    public Attribute setFloat(double floatVal) {
        this.floatVal = floatVal;
        return this;
    }

    @JsonProperty("Int")
    public Long getInt() {
        return intVal;
    }

    public Attribute setInt(Long intVal) {
        this.intVal = intVal;
        return this;
    }

    @JsonProperty("String")
    public String getString() {
        return stringVal;
    }

    public Attribute setString(String stringVal) {
        this.stringVal = stringVal;
        return this;
    }

    @JsonProperty("Bool")
    public Boolean getBool() {
        return boolVal;
    }

    public Attribute setBool(Boolean boolVal) {
        this.boolVal = boolVal;
        return this;
    }

    @JsonProperty("Unit")
    public String getUnit() {
        return unit;
    }

    public Attribute setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Attribute fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Attribute.class);
    }

    public static List<Attribute> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Attribute.class);
    }
}
