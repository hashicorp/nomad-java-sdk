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
public final class Service extends ApiObject {
    private String id;
    private String name;
    private List<String> tags;
    private List<String> canaryTags;
    private boolean enableTagOverride;
    private String portLabel;
    private String addressMode;
    private List<ServiceCheck> checks;
    private CheckRestart checkRestart;
    private ConsulConnect connect;
    private Map<String, String> meta;
    private Map<String, String> canaryMeta;

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    public Service setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public Service setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Tags")
    public List<String> getTags() {
        return tags;
    }

    public Service setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Service addTags(String... tags) {
        if (this.tags == null)
            this.tags = new java.util.ArrayList<>();
        for (String item : tags)
            this.tags.add(item);
        return this;
    }

    @JsonProperty("CanaryTags")
    public List<String> getCanaryTags() {
        return canaryTags;
    }

    public Service setCanaryTags(List<String> canaryTags) {
        this.canaryTags = canaryTags;
        return this;
    }

    public Service addCanaryTags(String... canaryTags) {
        if (this.canaryTags == null)
            this.canaryTags = new java.util.ArrayList<>();
        for (String item : canaryTags)
            this.canaryTags.add(item);
        return this;
    }

    @JsonProperty("EnableTagOverride")
    public boolean getEnableTagOverride() {
        return enableTagOverride;
    }

    public Service setEnableTagOverride(boolean enableTagOverride) {
        this.enableTagOverride = enableTagOverride;
        return this;
    }

    @JsonProperty("PortLabel")
    public String getPortLabel() {
        return portLabel;
    }

    public Service setPortLabel(String portLabel) {
        this.portLabel = portLabel;
        return this;
    }

    @JsonProperty("AddressMode")
    public String getAddressMode() {
        return addressMode;
    }

    public Service setAddressMode(String addressMode) {
        this.addressMode = addressMode;
        return this;
    }

    @JsonProperty("Checks")
    public List<ServiceCheck> getChecks() {
        return checks;
    }

    public Service setChecks(List<ServiceCheck> checks) {
        this.checks = checks;
        return this;
    }

    public Service addChecks(ServiceCheck... checks) {
        if (this.checks == null)
            this.checks = new java.util.ArrayList<>();
        for (ServiceCheck item : checks)
            this.checks.add(item);
        return this;
    }

    @JsonProperty("CheckRestart")
    public CheckRestart getCheckRestart() {
        return checkRestart;
    }

    public Service setCheckRestart(CheckRestart checkRestart) {
        this.checkRestart = checkRestart;
        return this;
    }

    @JsonProperty("Connect")
    public ConsulConnect getConnect() {
        return connect;
    }

    public Service setConnect(ConsulConnect connect) {
        this.connect = connect;
        return this;
    }

    @JsonProperty("Meta")
    public Map<String, String> getMeta() {
        return meta;
    }

    public Service setMeta(Map<String, String> meta) {
        this.meta = meta;
        return this;
    }

    public Service addMeta(String key, String value) {
        if (this.meta == null)
            this.meta = new java.util.HashMap<>();
        this.meta.put(key, value);
        return this;
    }

    @JsonProperty("CanaryMeta")
    public Map<String, String> getCanaryMeta() {
        return canaryMeta;
    }

    public Service setCanaryMeta(Map<String, String> canaryMeta) {
        this.canaryMeta = canaryMeta;
        return this;
    }

    public Service addCanaryMeta(String key, String value) {
        if (this.canaryMeta == null)
            this.canaryMeta = new java.util.HashMap<>();
        this.canaryMeta.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Service fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Service.class);
    }

    public static List<Service> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Service.class);
    }
}
