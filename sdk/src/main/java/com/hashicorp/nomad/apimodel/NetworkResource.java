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
public final class NetworkResource extends ApiObject {
    private String mode;
    private String device;
    private String cidr;
    private String ip;
    private Integer mBits;
    private DnsConfig dns;
    private List<Port> reservedPorts;
    private List<Port> dynamicPorts;

    @JsonProperty("Mode")
    public String getMode() {
        return mode;
    }

    public NetworkResource setMode(String mode) {
        this.mode = mode;
        return this;
    }

    @JsonProperty("Device")
    public String getDevice() {
        return device;
    }

    public NetworkResource setDevice(String device) {
        this.device = device;
        return this;
    }

    @JsonProperty("CIDR")
    public String getCidr() {
        return cidr;
    }

    public NetworkResource setCidr(String cidr) {
        this.cidr = cidr;
        return this;
    }

    @JsonProperty("IP")
    public String getIp() {
        return ip;
    }

    public NetworkResource setIp(String ip) {
        this.ip = ip;
        return this;
    }

    @JsonProperty("MBits")
    public Integer getMBits() {
        return mBits;
    }

    public NetworkResource setMBits(Integer mBits) {
        this.mBits = mBits;
        return this;
    }

    @JsonProperty("DNS")
    public DnsConfig getDns() {
        return dns;
    }

    public NetworkResource setDns(DnsConfig dns) {
        this.dns = dns;
        return this;
    }

    @JsonProperty("ReservedPorts")
    public List<Port> getReservedPorts() {
        return reservedPorts;
    }

    public NetworkResource setReservedPorts(List<Port> reservedPorts) {
        this.reservedPorts = reservedPorts;
        return this;
    }

    public NetworkResource addReservedPorts(Port... reservedPorts) {
        if (this.reservedPorts == null)
            this.reservedPorts = new java.util.ArrayList<>();
        for (Port item : reservedPorts)
            this.reservedPorts.add(item);
        return this;
    }

    @JsonProperty("DynamicPorts")
    public List<Port> getDynamicPorts() {
        return dynamicPorts;
    }

    public NetworkResource setDynamicPorts(List<Port> dynamicPorts) {
        this.dynamicPorts = dynamicPorts;
        return this;
    }

    public NetworkResource addDynamicPorts(Port... dynamicPorts) {
        if (this.dynamicPorts == null)
            this.dynamicPorts = new java.util.ArrayList<>();
        for (Port item : dynamicPorts)
            this.dynamicPorts.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NetworkResource fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NetworkResource.class);
    }

    public static List<NetworkResource> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NetworkResource.class);
    }
}
