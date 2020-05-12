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
public final class CsiPluginListStub extends ApiObject {
    private String id;
    private String provider;
    private boolean controllerRequired;
    private int controllersHealthy;
    private int controllersExpected;
    private int nodesHealthy;
    private int nodesExpected;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public CsiPluginListStub setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Provider")
    public String getProvider() {
        return provider;
    }

    public CsiPluginListStub setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    @JsonProperty("ControllerRequired")
    public boolean getControllerRequired() {
        return controllerRequired;
    }

    public CsiPluginListStub setControllerRequired(boolean controllerRequired) {
        this.controllerRequired = controllerRequired;
        return this;
    }

    @JsonProperty("ControllersHealthy")
    public int getControllersHealthy() {
        return controllersHealthy;
    }

    public CsiPluginListStub setControllersHealthy(int controllersHealthy) {
        this.controllersHealthy = controllersHealthy;
        return this;
    }

    @JsonProperty("ControllersExpected")
    public int getControllersExpected() {
        return controllersExpected;
    }

    public CsiPluginListStub setControllersExpected(int controllersExpected) {
        this.controllersExpected = controllersExpected;
        return this;
    }

    @JsonProperty("NodesHealthy")
    public int getNodesHealthy() {
        return nodesHealthy;
    }

    public CsiPluginListStub setNodesHealthy(int nodesHealthy) {
        this.nodesHealthy = nodesHealthy;
        return this;
    }

    @JsonProperty("NodesExpected")
    public int getNodesExpected() {
        return nodesExpected;
    }

    public CsiPluginListStub setNodesExpected(int nodesExpected) {
        this.nodesExpected = nodesExpected;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public CsiPluginListStub setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public CsiPluginListStub setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static CsiPluginListStub fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CsiPluginListStub.class);
    }

    public static List<CsiPluginListStub> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CsiPluginListStub.class);
    }
}
