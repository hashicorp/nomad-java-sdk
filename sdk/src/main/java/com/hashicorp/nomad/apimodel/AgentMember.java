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
public final class AgentMember extends ApiObject {
    private String name;
    private String addr;
    private int port;
    private Map<String, String> tags;
    private String status;
    private byte protocolMin;
    private byte protocolMax;
    private byte protocolCur;
    private byte delegateMin;
    private byte delegateMax;
    private byte delegateCur;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public AgentMember setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Addr")
    public String getAddr() {
        return addr;
    }

    public AgentMember setAddr(String addr) {
        this.addr = addr;
        return this;
    }

    @JsonProperty("Port")
    public int getPort() {
        return port;
    }

    public AgentMember setPort(int port) {
        this.port = port;
        return this;
    }

    @JsonProperty("Tags")
    public Map<String, String> getTags() {
        return tags;
    }

    public AgentMember setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public AgentMember addTags(String key, String value) {
        if (this.tags == null)
            this.tags = new java.util.HashMap<>();
        this.tags.put(key, value);
        return this;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    public AgentMember setStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("ProtocolMin")
    public byte getProtocolMin() {
        return protocolMin;
    }

    public AgentMember setProtocolMin(byte protocolMin) {
        this.protocolMin = protocolMin;
        return this;
    }

    @JsonProperty("ProtocolMax")
    public byte getProtocolMax() {
        return protocolMax;
    }

    public AgentMember setProtocolMax(byte protocolMax) {
        this.protocolMax = protocolMax;
        return this;
    }

    @JsonProperty("ProtocolCur")
    public byte getProtocolCur() {
        return protocolCur;
    }

    public AgentMember setProtocolCur(byte protocolCur) {
        this.protocolCur = protocolCur;
        return this;
    }

    @JsonProperty("DelegateMin")
    public byte getDelegateMin() {
        return delegateMin;
    }

    public AgentMember setDelegateMin(byte delegateMin) {
        this.delegateMin = delegateMin;
        return this;
    }

    @JsonProperty("DelegateMax")
    public byte getDelegateMax() {
        return delegateMax;
    }

    public AgentMember setDelegateMax(byte delegateMax) {
        this.delegateMax = delegateMax;
        return this;
    }

    @JsonProperty("DelegateCur")
    public byte getDelegateCur() {
        return delegateCur;
    }

    public AgentMember setDelegateCur(byte delegateCur) {
        this.delegateCur = delegateCur;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AgentMember fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AgentMember.class);
    }

    public static List<AgentMember> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AgentMember.class);
    }
}
