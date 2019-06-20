package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class NodeDeviceResource extends ApiObject {
    private String vendor;
    private String type;
    private String name;
    private List<NodeDevice> instances;
    private Map<String, Attribute> attributes;

    @JsonProperty("Vendor")
    public String getVendor() {
        return vendor;
    }

    public NodeDeviceResource setVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public NodeDeviceResource setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public NodeDeviceResource setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Instances")
    public List<NodeDevice> getInstances() {
        return instances;
    }

    public NodeDeviceResource setInstances(List<NodeDevice> instances) {
        this.instances = instances;
        return this;
    }

    public NodeDeviceResource addInstances(NodeDevice... instances) {
        if (this.instances == null)
            this.instances = new java.util.ArrayList<>();
        for (NodeDevice item : instances)
            this.instances.add(item);
        return this;
    }

    @JsonProperty("Attributes")
    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    public NodeDeviceResource setAttributes(Map<String, Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public NodeDeviceResource addAttributes(String key, Attribute value) {
        if (this.attributes == null)
            this.attributes = new java.util.HashMap<>();
        this.attributes.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeDeviceResource fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeDeviceResource.class);
    }

    public static List<NodeDeviceResource> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeDeviceResource.class);
    }
}
