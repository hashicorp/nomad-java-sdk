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
public final class NodeReservedNetworkResources extends ApiObject {
    private String reservedHostPorts;

    @JsonProperty("ReservedHostPorts")
    public String getReservedHostPorts() {
        return reservedHostPorts;
    }

    public NodeReservedNetworkResources setReservedHostPorts(String reservedHostPorts) {
        this.reservedHostPorts = reservedHostPorts;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeReservedNetworkResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeReservedNetworkResources.class);
    }

    public static List<NodeReservedNetworkResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeReservedNetworkResources.class);
    }
}
