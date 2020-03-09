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
public final class VolumeMount extends ApiObject {
    private String volume;
    private String destination;
    private boolean readOnly;

    @JsonProperty("Volume")
    public String getVolume() {
        return volume;
    }

    public VolumeMount setVolume(String volume) {
        this.volume = volume;
        return this;
    }

    @JsonProperty("Destination")
    public String getDestination() {
        return destination;
    }

    public VolumeMount setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    @JsonProperty("ReadOnly")
    public boolean getReadOnly() {
        return readOnly;
    }

    public VolumeMount setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static VolumeMount fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, VolumeMount.class);
    }

    public static List<VolumeMount> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, VolumeMount.class);
    }
}
