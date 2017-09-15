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
public final class Task extends ApiObject {
    private String name;
    private String driver;
    private String user;
    private Map<String, Object> config;
    private List<Constraint> constraints;
    private Map<String, String> env;
    private List<Service> services;
    private Resources resources;
    private Map<String, String> meta;
    private Long killTimeout;
    private LogConfig logConfig;
    private List<TaskArtifact> artifacts;
    private Vault vault;
    private List<Template> templates;
    private DispatchPayloadConfig dispatchPayload;
    private boolean leader;
    private long shutdownDelay;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Driver")
    public String getDriver() {
        return driver;
    }

    public Task setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    @JsonProperty("User")
    public String getUser() {
        return user;
    }

    public Task setUser(String user) {
        this.user = user;
        return this;
    }

    @JsonProperty("Config")
    public Map<String, Object> getConfig() {
        return config;
    }

    public Task setConfig(Map<String, Object> config) {
        this.config = config;
        return this;
    }

    public Task addConfig(String key, Object value) {
        if (this.config == null)
            this.config = new java.util.HashMap<>();
        this.config.put(key, value);
        return this;
    }

    @JsonProperty("Constraints")
    public List<Constraint> getConstraints() {
        return constraints;
    }

    public Task setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
        return this;
    }

    public Task addConstraints(Constraint... constraints) {
        if (this.constraints == null)
            this.constraints = new java.util.ArrayList<>();
        for (Constraint item : constraints)
            this.constraints.add(item);
        return this;
    }

    @JsonProperty("Env")
    public Map<String, String> getEnv() {
        return env;
    }

    public Task setEnv(Map<String, String> env) {
        this.env = env;
        return this;
    }

    public Task addEnv(String key, String value) {
        if (this.env == null)
            this.env = new java.util.HashMap<>();
        this.env.put(key, value);
        return this;
    }

    @JsonProperty("Services")
    public List<Service> getServices() {
        return services;
    }

    public Task setServices(List<Service> services) {
        this.services = services;
        return this;
    }

    public Task addServices(Service... services) {
        if (this.services == null)
            this.services = new java.util.ArrayList<>();
        for (Service item : services)
            this.services.add(item);
        return this;
    }

    @JsonProperty("Resources")
    public Resources getResources() {
        return resources;
    }

    public Task setResources(Resources resources) {
        this.resources = resources;
        return this;
    }

    @JsonProperty("Meta")
    public Map<String, String> getMeta() {
        return meta;
    }

    public Task setMeta(Map<String, String> meta) {
        this.meta = meta;
        return this;
    }

    public Task addMeta(String key, String value) {
        if (this.meta == null)
            this.meta = new java.util.HashMap<>();
        this.meta.put(key, value);
        return this;
    }

    @JsonProperty("KillTimeout")
    public Long getKillTimeout() {
        return killTimeout;
    }

    public Task setKillTimeout(Long killTimeout) {
        this.killTimeout = killTimeout;
        return this;
    }

    @JsonProperty("LogConfig")
    public LogConfig getLogConfig() {
        return logConfig;
    }

    public Task setLogConfig(LogConfig logConfig) {
        this.logConfig = logConfig;
        return this;
    }

    @JsonProperty("Artifacts")
    public List<TaskArtifact> getArtifacts() {
        return artifacts;
    }

    public Task setArtifacts(List<TaskArtifact> artifacts) {
        this.artifacts = artifacts;
        return this;
    }

    public Task addArtifacts(TaskArtifact... artifacts) {
        if (this.artifacts == null)
            this.artifacts = new java.util.ArrayList<>();
        for (TaskArtifact item : artifacts)
            this.artifacts.add(item);
        return this;
    }

    @JsonProperty("Vault")
    public Vault getVault() {
        return vault;
    }

    public Task setVault(Vault vault) {
        this.vault = vault;
        return this;
    }

    @JsonProperty("Templates")
    public List<Template> getTemplates() {
        return templates;
    }

    public Task setTemplates(List<Template> templates) {
        this.templates = templates;
        return this;
    }

    public Task addTemplates(Template... templates) {
        if (this.templates == null)
            this.templates = new java.util.ArrayList<>();
        for (Template item : templates)
            this.templates.add(item);
        return this;
    }

    @JsonProperty("DispatchPayload")
    public DispatchPayloadConfig getDispatchPayload() {
        return dispatchPayload;
    }

    public Task setDispatchPayload(DispatchPayloadConfig dispatchPayload) {
        this.dispatchPayload = dispatchPayload;
        return this;
    }

    @JsonProperty("Leader")
    public boolean getLeader() {
        return leader;
    }

    public Task setLeader(boolean leader) {
        this.leader = leader;
        return this;
    }

    @JsonProperty("ShutdownDelay")
    public long getShutdownDelay() {
        return shutdownDelay;
    }

    public Task setShutdownDelay(long shutdownDelay) {
        this.shutdownDelay = shutdownDelay;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Task fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Task.class);
    }

    public static List<Task> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Task.class);
    }
}
