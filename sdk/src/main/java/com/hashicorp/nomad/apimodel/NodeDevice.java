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
public final class NodeDevice extends ApiObject {
    private String id;
    private boolean healthy;
    private String healthDescription;
    private NodeDeviceLocality locality;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public NodeDevice setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Healthy")
    public boolean getHealthy() {
        return healthy;
    }

    public NodeDevice setHealthy(boolean healthy) {
        this.healthy = healthy;
        return this;
    }

    @JsonProperty("HealthDescription")
    public String getHealthDescription() {
        return healthDescription;
    }

    public NodeDevice setHealthDescription(String healthDescription) {
        this.healthDescription = healthDescription;
        return this;
    }

    @JsonProperty("Locality")
    public NodeDeviceLocality getLocality() {
        return locality;
    }

    public NodeDevice setLocality(NodeDeviceLocality locality) {
        this.locality = locality;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeDevice fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeDevice.class);
    }

    public static List<NodeDevice> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeDevice.class);
    }
}
