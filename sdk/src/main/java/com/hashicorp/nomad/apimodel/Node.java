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
public final class Node extends ApiObject {
    private String id;
    private String datacenter;
    private String name;
    private String httpAddr;
    private boolean tlsEnabled;
    private Map<String, String> attributes;
    private Resources resources;
    private Resources reserved;
    private NodeResources nodeResources;
    private NodeReservedResources reservedResources;
    private Map<String, String> links;
    private Map<String, String> meta;
    private String nodeClass;
    private boolean drain;
    private DrainStrategy drainStrategy;
    private String schedulingEligibility;
    private String status;
    private String statusDescription;
    private long statusUpdatedAt;
    private List<NodeEvent> events;
    private Map<String, DriverInfo> drivers;
    private Map<String, HostVolumeInfo> hostVolumes;
    private Map<String, CsiInfo> csiControllerPlugins;
    private Map<String, CsiInfo> csiNodePlugins;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public Node setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Datacenter")
    public String getDatacenter() {
        return datacenter;
    }

    public Node setDatacenter(String datacenter) {
        this.datacenter = datacenter;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public Node setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("HTTPAddr")
    public String getHttpAddr() {
        return httpAddr;
    }

    public Node setHttpAddr(String httpAddr) {
        this.httpAddr = httpAddr;
        return this;
    }

    @JsonProperty("TLSEnabled")
    public boolean getTlsEnabled() {
        return tlsEnabled;
    }

    public Node setTlsEnabled(boolean tlsEnabled) {
        this.tlsEnabled = tlsEnabled;
        return this;
    }

    @JsonProperty("Attributes")
    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Node setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Node addAttributes(String key, String value) {
        if (this.attributes == null)
            this.attributes = new java.util.HashMap<>();
        this.attributes.put(key, value);
        return this;
    }

    @JsonProperty("Resources")
    public Resources getResources() {
        return resources;
    }

    public Node setResources(Resources resources) {
        this.resources = resources;
        return this;
    }

    @JsonProperty("Reserved")
    public Resources getReserved() {
        return reserved;
    }

    public Node setReserved(Resources reserved) {
        this.reserved = reserved;
        return this;
    }

    @JsonProperty("NodeResources")
    public NodeResources getNodeResources() {
        return nodeResources;
    }

    public Node setNodeResources(NodeResources nodeResources) {
        this.nodeResources = nodeResources;
        return this;
    }

    @JsonProperty("ReservedResources")
    public NodeReservedResources getReservedResources() {
        return reservedResources;
    }

    public Node setReservedResources(NodeReservedResources reservedResources) {
        this.reservedResources = reservedResources;
        return this;
    }

    @JsonProperty("Links")
    public Map<String, String> getLinks() {
        return links;
    }

    public Node setLinks(Map<String, String> links) {
        this.links = links;
        return this;
    }

    public Node addLinks(String key, String value) {
        if (this.links == null)
            this.links = new java.util.HashMap<>();
        this.links.put(key, value);
        return this;
    }

    @JsonProperty("Meta")
    public Map<String, String> getMeta() {
        return meta;
    }

    public Node setMeta(Map<String, String> meta) {
        this.meta = meta;
        return this;
    }

    public Node addMeta(String key, String value) {
        if (this.meta == null)
            this.meta = new java.util.HashMap<>();
        this.meta.put(key, value);
        return this;
    }

    @JsonProperty("NodeClass")
    public String getNodeClass() {
        return nodeClass;
    }

    public Node setNodeClass(String nodeClass) {
        this.nodeClass = nodeClass;
        return this;
    }

    @JsonProperty("Drain")
    public boolean getDrain() {
        return drain;
    }

    public Node setDrain(boolean drain) {
        this.drain = drain;
        return this;
    }

    @JsonProperty("DrainStrategy")
    public DrainStrategy getDrainStrategy() {
        return drainStrategy;
    }

    public Node setDrainStrategy(DrainStrategy drainStrategy) {
        this.drainStrategy = drainStrategy;
        return this;
    }

    @JsonProperty("SchedulingEligibility")
    public String getSchedulingEligibility() {
        return schedulingEligibility;
    }

    public Node setSchedulingEligibility(String schedulingEligibility) {
        this.schedulingEligibility = schedulingEligibility;
        return this;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    public Node setStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("StatusDescription")
    public String getStatusDescription() {
        return statusDescription;
    }

    public Node setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
        return this;
    }

    @JsonProperty("StatusUpdatedAt")
    public long getStatusUpdatedAt() {
        return statusUpdatedAt;
    }

    public Node setStatusUpdatedAt(long statusUpdatedAt) {
        this.statusUpdatedAt = statusUpdatedAt;
        return this;
    }

    @JsonProperty("Events")
    public List<NodeEvent> getEvents() {
        return events;
    }

    public Node setEvents(List<NodeEvent> events) {
        this.events = events;
        return this;
    }

    public Node addEvents(NodeEvent... events) {
        if (this.events == null)
            this.events = new java.util.ArrayList<>();
        for (NodeEvent item : events)
            this.events.add(item);
        return this;
    }

    @JsonProperty("Drivers")
    public Map<String, DriverInfo> getDrivers() {
        return drivers;
    }

    public Node setDrivers(Map<String, DriverInfo> drivers) {
        this.drivers = drivers;
        return this;
    }

    public Node addDrivers(String key, DriverInfo value) {
        if (this.drivers == null)
            this.drivers = new java.util.HashMap<>();
        this.drivers.put(key, value);
        return this;
    }

    @JsonProperty("HostVolumes")
    public Map<String, HostVolumeInfo> getHostVolumes() {
        return hostVolumes;
    }

    public Node setHostVolumes(Map<String, HostVolumeInfo> hostVolumes) {
        this.hostVolumes = hostVolumes;
        return this;
    }

    public Node addHostVolumes(String key, HostVolumeInfo value) {
        if (this.hostVolumes == null)
            this.hostVolumes = new java.util.HashMap<>();
        this.hostVolumes.put(key, value);
        return this;
    }

    @JsonProperty("CSIControllerPlugins")
    public Map<String, CsiInfo> getCsiControllerPlugins() {
        return csiControllerPlugins;
    }

    public Node setCsiControllerPlugins(Map<String, CsiInfo> csiControllerPlugins) {
        this.csiControllerPlugins = csiControllerPlugins;
        return this;
    }

    public Node addCsiControllerPlugins(String key, CsiInfo value) {
        if (this.csiControllerPlugins == null)
            this.csiControllerPlugins = new java.util.HashMap<>();
        this.csiControllerPlugins.put(key, value);
        return this;
    }

    @JsonProperty("CSINodePlugins")
    public Map<String, CsiInfo> getCsiNodePlugins() {
        return csiNodePlugins;
    }

    public Node setCsiNodePlugins(Map<String, CsiInfo> csiNodePlugins) {
        this.csiNodePlugins = csiNodePlugins;
        return this;
    }

    public Node addCsiNodePlugins(String key, CsiInfo value) {
        if (this.csiNodePlugins == null)
            this.csiNodePlugins = new java.util.HashMap<>();
        this.csiNodePlugins.put(key, value);
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public Node setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public Node setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Node fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Node.class);
    }

    public static List<Node> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Node.class);
    }
}
