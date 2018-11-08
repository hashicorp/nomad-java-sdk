package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class RescheduleEvent extends ApiObject {
    private long rescheduleTime;
    private String prevAllocId;
    private String prevNodeId;

    @JsonProperty("RescheduleTime")
    public long getRescheduleTime() {
        return rescheduleTime;
    }

    public RescheduleEvent setRescheduleTime(long rescheduleTime) {
        this.rescheduleTime = rescheduleTime;
        return this;
    }

    @JsonProperty("PrevAllocID")
    public String getPrevAllocId() {
        return prevAllocId;
    }

    public RescheduleEvent setPrevAllocId(String prevAllocId) {
        this.prevAllocId = prevAllocId;
        return this;
    }

    @JsonProperty("PrevNodeID")
    public String getPrevNodeId() {
        return prevNodeId;
    }

    public RescheduleEvent setPrevNodeId(String prevNodeId) {
        this.prevNodeId = prevNodeId;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static RescheduleEvent fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, RescheduleEvent.class);
    }

    public static List<RescheduleEvent> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, RescheduleEvent.class);
    }
}
