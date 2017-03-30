package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class ServiceCheck {
    private String id;
    private String name;
    private String type;
    private String command;
    private List<String> args;
    private String path;
    private String protocol;
    private String portLabel;
    private long interval;
    private long timeout;
    private String initialStatus;

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    public ServiceCheck setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public ServiceCheck setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public ServiceCheck setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Command")
    public String getCommand() {
        return command;
    }

    public ServiceCheck setCommand(String command) {
        this.command = command;
        return this;
    }

    @JsonProperty("Args")
    public List<String> getArgs() {
        return args;
    }

    public ServiceCheck setArgs(List<String> args) {
        this.args = args;
        return this;
    }

    public ServiceCheck addArgs(String... args) {
        if (this.args == null)
            this.args = new java.util.ArrayList<>();
        for (String item : args)
            this.args.add(item);
        return this;
    }

    @JsonProperty("Path")
    public String getPath() {
        return path;
    }

    public ServiceCheck setPath(String path) {
        this.path = path;
        return this;
    }

    @JsonProperty("Protocol")
    public String getProtocol() {
        return protocol;
    }

    public ServiceCheck setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    @JsonProperty("PortLabel")
    public String getPortLabel() {
        return portLabel;
    }

    public ServiceCheck setPortLabel(String portLabel) {
        this.portLabel = portLabel;
        return this;
    }

    @JsonProperty("Interval")
    public long getInterval() {
        return interval;
    }

    public ServiceCheck setInterval(long interval) {
        this.interval = interval;
        return this;
    }

    @JsonProperty("Timeout")
    public long getTimeout() {
        return timeout;
    }

    public ServiceCheck setTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    @JsonProperty("InitialStatus")
    public String getInitialStatus() {
        return initialStatus;
    }

    public ServiceCheck setInitialStatus(String initialStatus) {
        this.initialStatus = initialStatus;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ServiceCheck fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ServiceCheck.class);
    }

    public static List<ServiceCheck> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ServiceCheck.class);
    }
}
