package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class CsiPlugin extends ApiObject {
    private String id;
    private String provider;
    private String version;
    private boolean controllerRequired;
    private Map<String, CsiInfo> controllers;
    private Map<String, CsiInfo> nodes;
    private List<AllocationListStub> allocations;
    private int controllersHealthy;
    private int nodesHealthy;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public CsiPlugin setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Provider")
    public String getProvider() {
        return provider;
    }

    public CsiPlugin setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    @JsonProperty("Version")
    public String getVersion() {
        return version;
    }

    public CsiPlugin setVersion(String version) {
        this.version = version;
        return this;
    }

    @JsonProperty("ControllerRequired")
    public boolean getControllerRequired() {
        return controllerRequired;
    }

    public CsiPlugin setControllerRequired(boolean controllerRequired) {
        this.controllerRequired = controllerRequired;
        return this;
    }

    @JsonProperty("Controllers")
    public Map<String, CsiInfo> getControllers() {
        return controllers;
    }

    public CsiPlugin setControllers(Map<String, CsiInfo> controllers) {
        this.controllers = controllers;
        return this;
    }

    public CsiPlugin addControllers(String key, CsiInfo value) {
        if (this.controllers == null)
            this.controllers = new java.util.HashMap<>();
        this.controllers.put(key, value);
        return this;
    }

    @JsonProperty("Nodes")
    public Map<String, CsiInfo> getNodes() {
        return nodes;
    }

    public CsiPlugin setNodes(Map<String, CsiInfo> nodes) {
        this.nodes = nodes;
        return this;
    }

    public CsiPlugin addNodes(String key, CsiInfo value) {
        if (this.nodes == null)
            this.nodes = new java.util.HashMap<>();
        this.nodes.put(key, value);
        return this;
    }

    @JsonProperty("Allocations")
    public List<AllocationListStub> getAllocations() {
        return allocations;
    }

    public CsiPlugin setAllocations(List<AllocationListStub> allocations) {
        this.allocations = allocations;
        return this;
    }

    public CsiPlugin addAllocations(AllocationListStub... allocations) {
        if (this.allocations == null)
            this.allocations = new java.util.ArrayList<>();
        for (AllocationListStub item : allocations)
            this.allocations.add(item);
        return this;
    }

    @JsonProperty("ControllersHealthy")
    public int getControllersHealthy() {
        return controllersHealthy;
    }

    public CsiPlugin setControllersHealthy(int controllersHealthy) {
        this.controllersHealthy = controllersHealthy;
        return this;
    }

    @JsonProperty("NodesHealthy")
    public int getNodesHealthy() {
        return nodesHealthy;
    }

    public CsiPlugin setNodesHealthy(int nodesHealthy) {
        this.nodesHealthy = nodesHealthy;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public CsiPlugin setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public CsiPlugin setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static CsiPlugin fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CsiPlugin.class);
    }

    public static List<CsiPlugin> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CsiPlugin.class);
    }
}
