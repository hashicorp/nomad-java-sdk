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
public final class PortMapping extends ApiObject {
    private String label;
    private int value;
    private int to;
    private String hostIp;

    @JsonProperty("Label")
    public String getLabel() {
        return label;
    }

    public PortMapping setLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("Value")
    public int getValue() {
        return value;
    }

    public PortMapping setValue(int value) {
        this.value = value;
        return this;
    }

    @JsonProperty("To")
    public int getTo() {
        return to;
    }

    public PortMapping setTo(int to) {
        this.to = to;
        return this;
    }

    @JsonProperty("HostIP")
    public String getHostIp() {
        return hostIp;
    }

    public PortMapping setHostIp(String hostIp) {
        this.hostIp = hostIp;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static PortMapping fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, PortMapping.class);
    }

    public static List<PortMapping> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, PortMapping.class);
    }
}
