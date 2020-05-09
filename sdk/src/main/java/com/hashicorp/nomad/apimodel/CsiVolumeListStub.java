package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class CsiVolumeListStub extends ApiObject {
    private String id;
    private String namespace;
    private String name;
    private String externalId;
    private List<CsiTopology> topologies;
    private String accessMode;
    private String attachmentMode;
    private CsiMountOptions mountOptions;
    private boolean schedulable;
    private String pluginId;
    private String provider;
    private boolean controllerRequired;
    private int controllersHealthy;
    private int controllersExpected;
    private int nodesHealthy;
    private int nodesExpected;
    private Date resourceExhausted;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public CsiVolumeListStub setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Namespace")
    public String getNamespace() {
        return namespace;
    }

    public CsiVolumeListStub setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public CsiVolumeListStub setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("ExternalID")
    public String getExternalId() {
        return externalId;
    }

    public CsiVolumeListStub setExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    @JsonProperty("Topologies")
    public List<CsiTopology> getTopologies() {
        return topologies;
    }

    public CsiVolumeListStub setTopologies(List<CsiTopology> topologies) {
        this.topologies = topologies;
        return this;
    }

    public CsiVolumeListStub addTopologies(CsiTopology... topologies) {
        if (this.topologies == null)
            this.topologies = new java.util.ArrayList<>();
        for (CsiTopology item : topologies)
            this.topologies.add(item);
        return this;
    }

    @JsonProperty("AccessMode")
    public String getAccessMode() {
        return accessMode;
    }

    public CsiVolumeListStub setAccessMode(String accessMode) {
        this.accessMode = accessMode;
        return this;
    }

    @JsonProperty("AttachmentMode")
    public String getAttachmentMode() {
        return attachmentMode;
    }

    public CsiVolumeListStub setAttachmentMode(String attachmentMode) {
        this.attachmentMode = attachmentMode;
        return this;
    }

    @JsonProperty("MountOptions")
    public CsiMountOptions getMountOptions() {
        return mountOptions;
    }

    public CsiVolumeListStub setMountOptions(CsiMountOptions mountOptions) {
        this.mountOptions = mountOptions;
        return this;
    }

    @JsonProperty("Schedulable")
    public boolean getSchedulable() {
        return schedulable;
    }

    public CsiVolumeListStub setSchedulable(boolean schedulable) {
        this.schedulable = schedulable;
        return this;
    }

    @JsonProperty("PluginID")
    public String getPluginId() {
        return pluginId;
    }

    public CsiVolumeListStub setPluginId(String pluginId) {
        this.pluginId = pluginId;
        return this;
    }

    @JsonProperty("Provider")
    public String getProvider() {
        return provider;
    }

    public CsiVolumeListStub setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    @JsonProperty("ControllerRequired")
    public boolean getControllerRequired() {
        return controllerRequired;
    }

    public CsiVolumeListStub setControllerRequired(boolean controllerRequired) {
        this.controllerRequired = controllerRequired;
        return this;
    }

    @JsonProperty("ControllersHealthy")
    public int getControllersHealthy() {
        return controllersHealthy;
    }

    public CsiVolumeListStub setControllersHealthy(int controllersHealthy) {
        this.controllersHealthy = controllersHealthy;
        return this;
    }

    @JsonProperty("ControllersExpected")
    public int getControllersExpected() {
        return controllersExpected;
    }

    public CsiVolumeListStub setControllersExpected(int controllersExpected) {
        this.controllersExpected = controllersExpected;
        return this;
    }

    @JsonProperty("NodesHealthy")
    public int getNodesHealthy() {
        return nodesHealthy;
    }

    public CsiVolumeListStub setNodesHealthy(int nodesHealthy) {
        this.nodesHealthy = nodesHealthy;
        return this;
    }

    @JsonProperty("NodesExpected")
    public int getNodesExpected() {
        return nodesExpected;
    }

    public CsiVolumeListStub setNodesExpected(int nodesExpected) {
        this.nodesExpected = nodesExpected;
        return this;
    }

    @JsonProperty("ResourceExhausted")
    public Date getResourceExhausted() {
        return resourceExhausted;
    }

    public CsiVolumeListStub setResourceExhausted(Date resourceExhausted) {
        this.resourceExhausted = resourceExhausted;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public CsiVolumeListStub setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public CsiVolumeListStub setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static CsiVolumeListStub fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CsiVolumeListStub.class);
    }

    public static List<CsiVolumeListStub> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CsiVolumeListStub.class);
    }
}
