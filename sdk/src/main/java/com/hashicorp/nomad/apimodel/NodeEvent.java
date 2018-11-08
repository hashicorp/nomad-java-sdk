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
public final class NodeEvent extends ApiObject {
    private String message;
    private String subsystem;
    private Map<String, String> details;
    private Date timestamp;
    private BigInteger createIndex;

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    public NodeEvent setMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonProperty("Subsystem")
    public String getSubsystem() {
        return subsystem;
    }

    public NodeEvent setSubsystem(String subsystem) {
        this.subsystem = subsystem;
        return this;
    }

    @JsonProperty("Details")
    public Map<String, String> getDetails() {
        return details;
    }

    public NodeEvent setDetails(Map<String, String> details) {
        this.details = details;
        return this;
    }

    public NodeEvent addDetails(String key, String value) {
        if (this.details == null)
            this.details = new java.util.HashMap<>();
        this.details.put(key, value);
        return this;
    }

    @JsonProperty("Timestamp")
    public Date getTimestamp() {
        return timestamp;
    }

    public NodeEvent setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public NodeEvent setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeEvent fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeEvent.class);
    }

    public static List<NodeEvent> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeEvent.class);
    }
}
