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
public final class CsiControllerInfo extends ApiObject {
    private boolean supportsReadOnlyAttach;
    private boolean supportsAttachDetach;
    private boolean supportsListVolumes;
    private boolean supportsListVolumesAttachedNodes;

    @JsonProperty("SupportsReadOnlyAttach")
    public boolean getSupportsReadOnlyAttach() {
        return supportsReadOnlyAttach;
    }

    public CsiControllerInfo setSupportsReadOnlyAttach(boolean supportsReadOnlyAttach) {
        this.supportsReadOnlyAttach = supportsReadOnlyAttach;
        return this;
    }

    @JsonProperty("SupportsAttachDetach")
    public boolean getSupportsAttachDetach() {
        return supportsAttachDetach;
    }

    public CsiControllerInfo setSupportsAttachDetach(boolean supportsAttachDetach) {
        this.supportsAttachDetach = supportsAttachDetach;
        return this;
    }

    @JsonProperty("SupportsListVolumes")
    public boolean getSupportsListVolumes() {
        return supportsListVolumes;
    }

    public CsiControllerInfo setSupportsListVolumes(boolean supportsListVolumes) {
        this.supportsListVolumes = supportsListVolumes;
        return this;
    }

    @JsonProperty("SupportsListVolumesAttachedNodes")
    public boolean getSupportsListVolumesAttachedNodes() {
        return supportsListVolumesAttachedNodes;
    }

    public CsiControllerInfo setSupportsListVolumesAttachedNodes(boolean supportsListVolumesAttachedNodes) {
        this.supportsListVolumesAttachedNodes = supportsListVolumesAttachedNodes;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static CsiControllerInfo fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CsiControllerInfo.class);
    }

    public static List<CsiControllerInfo> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CsiControllerInfo.class);
    }
}
