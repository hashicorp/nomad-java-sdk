package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class ServerHealth extends ApiObject {
    private String id;
    private String name;
    private String address;
    private String serfStatus;
    private String version;
    private boolean leader;
    private String lastContact;  // NOTE: the API generator will try to convert this to long but
                                 // the Nomad HTTP API emits it as a string; do not change
    private BigInteger lastTerm;
    private BigInteger lastIndex;
    private boolean healthy;
    private boolean voter;
    private Date stableSince;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public ServerHealth setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public ServerHealth setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Address")
    public String getAddress() {
        return address;
    }

    public ServerHealth setAddress(String address) {
        this.address = address;
        return this;
    }

    @JsonProperty("SerfStatus")
    public String getSerfStatus() {
        return serfStatus;
    }

    public ServerHealth setSerfStatus(String serfStatus) {
        this.serfStatus = serfStatus;
        return this;
    }

    @JsonProperty("Version")
    public String getVersion() {
        return version;
    }

    public ServerHealth setVersion(String version) {
        this.version = version;
        return this;
    }

    @JsonProperty("Leader")
    public boolean getLeader() {
        return leader;
    }

    public ServerHealth setLeader(boolean leader) {
        this.leader = leader;
        return this;
    }

    @JsonProperty("LastContact")
    public String getLastContact() {
        return lastContact;
    }

    public ServerHealth setLastContact(String lastContact) {
        this.lastContact = lastContact;
        return this;
    }

    @JsonProperty("LastTerm")
    public BigInteger getLastTerm() {
        return lastTerm;
    }

    public ServerHealth setLastTerm(BigInteger lastTerm) {
        this.lastTerm = lastTerm;
        return this;
    }

    @JsonProperty("LastIndex")
    public BigInteger getLastIndex() {
        return lastIndex;
    }

    public ServerHealth setLastIndex(BigInteger lastIndex) {
        this.lastIndex = lastIndex;
        return this;
    }

    @JsonProperty("Healthy")
    public boolean getHealthy() {
        return healthy;
    }

    public ServerHealth setHealthy(boolean healthy) {
        this.healthy = healthy;
        return this;
    }

    @JsonProperty("Voter")
    public boolean getVoter() {
        return voter;
    }

    public ServerHealth setVoter(boolean voter) {
        this.voter = voter;
        return this;
    }

    @JsonProperty("StableSince")
    public Date getStableSince() {
        return stableSince;
    }

    public ServerHealth setStableSince(Date stableSince) {
        this.stableSince = stableSince;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ServerHealth fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ServerHealth.class);
    }

    public static List<ServerHealth> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ServerHealth.class);
    }
}
