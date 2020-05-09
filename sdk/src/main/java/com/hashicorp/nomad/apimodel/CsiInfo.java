package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class CsiInfo extends ApiObject {
    private String pluginId;
    private boolean healthy;
    private String healthDescription;
    private Date updateTime;
    private boolean requiresControllerPlugin;
    private boolean requiresTopologies;
    private CsiControllerInfo controllerInfo;
    private CsiNodeInfo nodeInfo;

    @JsonProperty("PluginID")
    public String getPluginId() {
        return pluginId;
    }

    public CsiInfo setPluginId(String pluginId) {
        this.pluginId = pluginId;
        return this;
    }

    @JsonProperty("Healthy")
    public boolean getHealthy() {
        return healthy;
    }

    public CsiInfo setHealthy(boolean healthy) {
        this.healthy = healthy;
        return this;
    }

    @JsonProperty("HealthDescription")
    public String getHealthDescription() {
        return healthDescription;
    }

    public CsiInfo setHealthDescription(String healthDescription) {
        this.healthDescription = healthDescription;
        return this;
    }

    @JsonProperty("UpdateTime")
    public Date getUpdateTime() {
        return updateTime;
    }

    public CsiInfo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @JsonProperty("RequiresControllerPlugin")
    public boolean getRequiresControllerPlugin() {
        return requiresControllerPlugin;
    }

    public CsiInfo setRequiresControllerPlugin(boolean requiresControllerPlugin) {
        this.requiresControllerPlugin = requiresControllerPlugin;
        return this;
    }

    @JsonProperty("RequiresTopologies")
    public boolean getRequiresTopologies() {
        return requiresTopologies;
    }

    public CsiInfo setRequiresTopologies(boolean requiresTopologies) {
        this.requiresTopologies = requiresTopologies;
        return this;
    }

    @JsonProperty("ControllerInfo")
    public CsiControllerInfo getControllerInfo() {
        return controllerInfo;
    }

    public CsiInfo setControllerInfo(CsiControllerInfo controllerInfo) {
        this.controllerInfo = controllerInfo;
        return this;
    }

    @JsonProperty("NodeInfo")
    public CsiNodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public CsiInfo setNodeInfo(CsiNodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static CsiInfo fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CsiInfo.class);
    }

    public static List<CsiInfo> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CsiInfo.class);
    }
}
