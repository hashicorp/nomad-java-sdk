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
public final class CsiNodeInfo extends ApiObject {
    private String id;
    private long maxVolumes;
    private CsiTopology accessibleTopology;
    private boolean requiresNodeStageVolume;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public CsiNodeInfo setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("MaxVolumes")
    public long getMaxVolumes() {
        return maxVolumes;
    }

    public CsiNodeInfo setMaxVolumes(long maxVolumes) {
        this.maxVolumes = maxVolumes;
        return this;
    }

    @JsonProperty("AccessibleTopology")
    public CsiTopology getAccessibleTopology() {
        return accessibleTopology;
    }

    public CsiNodeInfo setAccessibleTopology(CsiTopology accessibleTopology) {
        this.accessibleTopology = accessibleTopology;
        return this;
    }

    @JsonProperty("RequiresNodeStageVolume")
    public boolean getRequiresNodeStageVolume() {
        return requiresNodeStageVolume;
    }

    public CsiNodeInfo setRequiresNodeStageVolume(boolean requiresNodeStageVolume) {
        this.requiresNodeStageVolume = requiresNodeStageVolume;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static CsiNodeInfo fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CsiNodeInfo.class);
    }

    public static List<CsiNodeInfo> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CsiNodeInfo.class);
    }
}
