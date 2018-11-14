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
public final class RequestedDevice extends ApiObject {
    private String name;
    private BigInteger count;
    private List<Constraint> constraints;
    private List<Affinity> affinities;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public RequestedDevice setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Count")
    public BigInteger getCount() {
        return count;
    }

    public RequestedDevice setCount(BigInteger count) {
        this.count = count;
        return this;
    }

    @JsonProperty("Constraints")
    public List<Constraint> getConstraints() {
        return constraints;
    }

    public RequestedDevice setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
        return this;
    }

    public RequestedDevice addConstraints(Constraint... constraints) {
        if (this.constraints == null)
            this.constraints = new java.util.ArrayList<>();
        for (Constraint item : constraints)
            this.constraints.add(item);
        return this;
    }

    @JsonProperty("Affinities")
    public List<Affinity> getAffinities() {
        return affinities;
    }

    public RequestedDevice setAffinities(List<Affinity> affinities) {
        this.affinities = affinities;
        return this;
    }

    public RequestedDevice addAffinities(Affinity... affinities) {
        if (this.affinities == null)
            this.affinities = new java.util.ArrayList<>();
        for (Affinity item : affinities)
            this.affinities.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static RequestedDevice fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, RequestedDevice.class);
    }

    public static List<RequestedDevice> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, RequestedDevice.class);
    }
}
