package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class CsiVolume extends ApiObject {
    private String id;
    private String name;
    private String externalId;
    private String namespace;
    private List<CsiTopology> topologies;
    private String accessMode;
    private String attachmentMode;
    private CsiMountOptions mountOptions;
    private Map<String, String> secrets;
    private Map<String, String> parameters;
    private Map<String, String> context;
    private Map<String, Allocation> readAllocs;
    private Map<String, Allocation> writeAllocs;
    private List<AllocationListStub> allocations;
    private boolean schedulable;
    private String pluginId;
    private String provider;
    private String providerVersion;
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

    public CsiVolume setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public CsiVolume setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("ExternalID")
    public String getExternalId() {
        return externalId;
    }

    public CsiVolume setExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    @JsonProperty("Namespace")
    public String getNamespace() {
        return namespace;
    }

    public CsiVolume setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty("Topologies")
    public List<CsiTopology> getTopologies() {
        return topologies;
    }

    public CsiVolume setTopologies(List<CsiTopology> topologies) {
        this.topologies = topologies;
        return this;
    }

    public CsiVolume addTopologies(CsiTopology... topologies) {
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

    public CsiVolume setAccessMode(String accessMode) {
        this.accessMode = accessMode;
        return this;
    }

    @JsonProperty("AttachmentMode")
    public String getAttachmentMode() {
        return attachmentMode;
    }

    public CsiVolume setAttachmentMode(String attachmentMode) {
        this.attachmentMode = attachmentMode;
        return this;
    }

    @JsonProperty("MountOptions")
    public CsiMountOptions getMountOptions() {
        return mountOptions;
    }

    public CsiVolume setMountOptions(CsiMountOptions mountOptions) {
        this.mountOptions = mountOptions;
        return this;
    }

    @JsonProperty("Secrets")
    public Map<String, String> getSecrets() {
        return secrets;
    }

    public CsiVolume setSecrets(Map<String, String> secrets) {
        this.secrets = secrets;
        return this;
    }

    public CsiVolume addSecrets(String key, String value) {
        if (this.secrets == null)
            this.secrets = new java.util.HashMap<>();
        this.secrets.put(key, value);
        return this;
    }

    @JsonProperty("Parameters")
    public Map<String, String> getParameters() {
        return parameters;
    }

    public CsiVolume setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public CsiVolume addParameters(String key, String value) {
        if (this.parameters == null)
            this.parameters = new java.util.HashMap<>();
        this.parameters.put(key, value);
        return this;
    }

    @JsonProperty("Context")
    public Map<String, String> getContext() {
        return context;
    }

    public CsiVolume setContext(Map<String, String> context) {
        this.context = context;
        return this;
    }

    public CsiVolume addContext(String key, String value) {
        if (this.context == null)
            this.context = new java.util.HashMap<>();
        this.context.put(key, value);
        return this;
    }

    @JsonProperty("ReadAllocs")
    public Map<String, Allocation> getReadAllocs() {
        return readAllocs;
    }

    public CsiVolume setReadAllocs(Map<String, Allocation> readAllocs) {
        this.readAllocs = readAllocs;
        return this;
    }

    public CsiVolume addReadAllocs(String key, Allocation value) {
        if (this.readAllocs == null)
            this.readAllocs = new java.util.HashMap<>();
        this.readAllocs.put(key, value);
        return this;
    }

    @JsonProperty("WriteAllocs")
    public Map<String, Allocation> getWriteAllocs() {
        return writeAllocs;
    }

    public CsiVolume setWriteAllocs(Map<String, Allocation> writeAllocs) {
        this.writeAllocs = writeAllocs;
        return this;
    }

    public CsiVolume addWriteAllocs(String key, Allocation value) {
        if (this.writeAllocs == null)
            this.writeAllocs = new java.util.HashMap<>();
        this.writeAllocs.put(key, value);
        return this;
    }

    @JsonProperty("Allocations")
    public List<AllocationListStub> getAllocations() {
        return allocations;
    }

    public CsiVolume setAllocations(List<AllocationListStub> allocations) {
        this.allocations = allocations;
        return this;
    }

    public CsiVolume addAllocations(AllocationListStub... allocations) {
        if (this.allocations == null)
            this.allocations = new java.util.ArrayList<>();
        for (AllocationListStub item : allocations)
            this.allocations.add(item);
        return this;
    }

    @JsonProperty("Schedulable")
    public boolean getSchedulable() {
        return schedulable;
    }

    public CsiVolume setSchedulable(boolean schedulable) {
        this.schedulable = schedulable;
        return this;
    }

    @JsonProperty("PluginID")
    public String getPluginId() {
        return pluginId;
    }

    public CsiVolume setPluginId(String pluginId) {
        this.pluginId = pluginId;
        return this;
    }

    @JsonProperty("Provider")
    public String getProvider() {
        return provider;
    }

    public CsiVolume setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    @JsonProperty("ProviderVersion")
    public String getProviderVersion() {
        return providerVersion;
    }

    public CsiVolume setProviderVersion(String providerVersion) {
        this.providerVersion = providerVersion;
        return this;
    }

    @JsonProperty("ControllerRequired")
    public boolean getControllerRequired() {
        return controllerRequired;
    }

    public CsiVolume setControllerRequired(boolean controllerRequired) {
        this.controllerRequired = controllerRequired;
        return this;
    }

    @JsonProperty("ControllersHealthy")
    public int getControllersHealthy() {
        return controllersHealthy;
    }

    public CsiVolume setControllersHealthy(int controllersHealthy) {
        this.controllersHealthy = controllersHealthy;
        return this;
    }

    @JsonProperty("ControllersExpected")
    public int getControllersExpected() {
        return controllersExpected;
    }

    public CsiVolume setControllersExpected(int controllersExpected) {
        this.controllersExpected = controllersExpected;
        return this;
    }

    @JsonProperty("NodesHealthy")
    public int getNodesHealthy() {
        return nodesHealthy;
    }

    public CsiVolume setNodesHealthy(int nodesHealthy) {
        this.nodesHealthy = nodesHealthy;
        return this;
    }

    @JsonProperty("NodesExpected")
    public int getNodesExpected() {
        return nodesExpected;
    }

    public CsiVolume setNodesExpected(int nodesExpected) {
        this.nodesExpected = nodesExpected;
        return this;
    }

    @JsonProperty("ResourceExhausted")
    public Date getResourceExhausted() {
        return resourceExhausted;
    }

    public CsiVolume setResourceExhausted(Date resourceExhausted) {
        this.resourceExhausted = resourceExhausted;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public CsiVolume setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public CsiVolume setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static CsiVolume fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CsiVolume.class);
    }

    public static List<CsiVolume> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CsiVolume.class);
    }
}
