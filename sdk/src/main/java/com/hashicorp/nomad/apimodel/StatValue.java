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
public final class StatValue extends ApiObject {
    private double floatNumeratorVal;
    private double floatDenominatorVal;
    private Long intNumeratorVal;
    private Long intDenominatorVal;
    private String stringVal;
    private Boolean boolVal;
    private String unit;
    private String desc;

    @JsonProperty("FloatNumeratorVal")
    public double getFloatNumeratorVal() {
        return floatNumeratorVal;
    }

    public StatValue setFloatNumeratorVal(double floatNumeratorVal) {
        this.floatNumeratorVal = floatNumeratorVal;
        return this;
    }

    @JsonProperty("FloatDenominatorVal")
    public double getFloatDenominatorVal() {
        return floatDenominatorVal;
    }

    public StatValue setFloatDenominatorVal(double floatDenominatorVal) {
        this.floatDenominatorVal = floatDenominatorVal;
        return this;
    }

    @JsonProperty("IntNumeratorVal")
    public Long getIntNumeratorVal() {
        return intNumeratorVal;
    }

    public StatValue setIntNumeratorVal(Long intNumeratorVal) {
        this.intNumeratorVal = intNumeratorVal;
        return this;
    }

    @JsonProperty("IntDenominatorVal")
    public Long getIntDenominatorVal() {
        return intDenominatorVal;
    }

    public StatValue setIntDenominatorVal(Long intDenominatorVal) {
        this.intDenominatorVal = intDenominatorVal;
        return this;
    }

    @JsonProperty("StringVal")
    public String getStringVal() {
        return stringVal;
    }

    public StatValue setStringVal(String stringVal) {
        this.stringVal = stringVal;
        return this;
    }

    @JsonProperty("BoolVal")
    public Boolean getBoolVal() {
        return boolVal;
    }

    public StatValue setBoolVal(Boolean boolVal) {
        this.boolVal = boolVal;
        return this;
    }

    @JsonProperty("Unit")
    public String getUnit() {
        return unit;
    }

    public StatValue setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    @JsonProperty("Desc")
    public String getDesc() {
        return desc;
    }

    public StatValue setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static StatValue fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, StatValue.class);
    }

    public static List<StatValue> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, StatValue.class);
    }
}
