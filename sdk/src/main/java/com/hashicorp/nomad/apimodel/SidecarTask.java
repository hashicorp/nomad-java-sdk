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
public final class SidecarTask extends ApiObject {
    private String name;
    private String driver;
    private String user;
    private Map<String, Object> config;
    private Map<String, String> env;
    private Resources resources;
    private Map<String, String> meta;
    private Long killTimeout;
    private LogConfig logConfig;
    private Long shutdownDelay;
    private String killSignal;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public SidecarTask setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Driver")
    public String getDriver() {
        return driver;
    }

    public SidecarTask setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    @JsonProperty("User")
    public String getUser() {
        return user;
    }

    public SidecarTask setUser(String user) {
        this.user = user;
        return this;
    }

    @JsonProperty("Config")
    public Map<String, Object> getConfig() {
        return config;
    }

    public SidecarTask setConfig(Map<String, Object> config) {
        this.config = config;
        return this;
    }

    public SidecarTask addConfig(String key, Object value) {
        if (this.config == null)
            this.config = new java.util.HashMap<>();
        this.config.put(key, value);
        return this;
    }

    @JsonProperty("Env")
    public Map<String, String> getEnv() {
        return env;
    }

    public SidecarTask setEnv(Map<String, String> env) {
        this.env = env;
        return this;
    }

    public SidecarTask addEnv(String key, String value) {
        if (this.env == null)
            this.env = new java.util.HashMap<>();
        this.env.put(key, value);
        return this;
    }

    @JsonProperty("Resources")
    public Resources getResources() {
        return resources;
    }

    public SidecarTask setResources(Resources resources) {
        this.resources = resources;
        return this;
    }

    @JsonProperty("Meta")
    public Map<String, String> getMeta() {
        return meta;
    }

    public SidecarTask setMeta(Map<String, String> meta) {
        this.meta = meta;
        return this;
    }

    public SidecarTask addMeta(String key, String value) {
        if (this.meta == null)
            this.meta = new java.util.HashMap<>();
        this.meta.put(key, value);
        return this;
    }

    @JsonProperty("KillTimeout")
    public Long getKillTimeout() {
        return killTimeout;
    }

    public SidecarTask setKillTimeout(Long killTimeout) {
        this.killTimeout = killTimeout;
        return this;
    }

    @JsonProperty("LogConfig")
    public LogConfig getLogConfig() {
        return logConfig;
    }

    public SidecarTask setLogConfig(LogConfig logConfig) {
        this.logConfig = logConfig;
        return this;
    }

    @JsonProperty("ShutdownDelay")
    public Long getShutdownDelay() {
        return shutdownDelay;
    }

    public SidecarTask setShutdownDelay(Long shutdownDelay) {
        this.shutdownDelay = shutdownDelay;
        return this;
    }

    @JsonProperty("KillSignal")
    public String getKillSignal() {
        return killSignal;
    }

    public SidecarTask setKillSignal(String killSignal) {
        this.killSignal = killSignal;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static SidecarTask fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, SidecarTask.class);
    }

    public static List<SidecarTask> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, SidecarTask.class);
    }
}
