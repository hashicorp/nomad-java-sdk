package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class AutopilotConfiguration extends ApiObject {
    private boolean cleanupDeadServers;
    private String lastContactThreshold;
    private BigInteger maxTrailingLogs;
    private String serverStabilizationTime;
    private boolean enableRedundancyZones;
    private boolean disableUpgradeMigration;
    private boolean enableCustomUpgrades;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("CleanupDeadServers")
    public boolean getCleanupDeadServers() {
        return cleanupDeadServers;
    }

    public AutopilotConfiguration setCleanupDeadServers(boolean cleanupDeadServers) {
        this.cleanupDeadServers = cleanupDeadServers;
        return this;
    }

    @JsonProperty("LastContactThreshold")
    public String getLastContactThreshold() {
        return lastContactThreshold;
    }

    public AutopilotConfiguration setLastContactThreshold(String lastContactThreshold) {
        this.lastContactThreshold = lastContactThreshold;
        return this;
    }

    @JsonProperty("MaxTrailingLogs")
    public BigInteger getMaxTrailingLogs() {
        return maxTrailingLogs;
    }

    public AutopilotConfiguration setMaxTrailingLogs(BigInteger maxTrailingLogs) {
        this.maxTrailingLogs = maxTrailingLogs;
        return this;
    }

    @JsonProperty("ServerStabilizationTime")
    public String getServerStabilizationTime() {
        return serverStabilizationTime;
    }

    public AutopilotConfiguration setServerStabilizationTime(String serverStabilizationTime) {
        this.serverStabilizationTime = serverStabilizationTime;
        return this;
    }

    @JsonProperty("EnableRedundancyZones")
    public boolean getEnableRedundancyZones() {
        return enableRedundancyZones;
    }

    public AutopilotConfiguration setEnableRedundancyZones(boolean enableRedundancyZones) {
        this.enableRedundancyZones = enableRedundancyZones;
        return this;
    }

    @JsonProperty("DisableUpgradeMigration")
    public boolean getDisableUpgradeMigration() {
        return disableUpgradeMigration;
    }

    public AutopilotConfiguration setDisableUpgradeMigration(boolean disableUpgradeMigration) {
        this.disableUpgradeMigration = disableUpgradeMigration;
        return this;
    }

    @JsonProperty("EnableCustomUpgrades")
    public boolean getEnableCustomUpgrades() {
        return enableCustomUpgrades;
    }

    public AutopilotConfiguration setEnableCustomUpgrades(boolean enableCustomUpgrades) {
        this.enableCustomUpgrades = enableCustomUpgrades;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public AutopilotConfiguration setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public AutopilotConfiguration setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AutopilotConfiguration fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AutopilotConfiguration.class);
    }

    public static List<AutopilotConfiguration> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AutopilotConfiguration.class);
    }
}
