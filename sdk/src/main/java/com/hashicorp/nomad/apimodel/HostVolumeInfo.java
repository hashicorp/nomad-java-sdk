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
public final class HostVolumeInfo extends ApiObject {
    private String path;
    private boolean readOnly;

    @JsonProperty("Path")
    public String getPath() {
        return path;
    }

    public HostVolumeInfo setPath(String path) {
        this.path = path;
        return this;
    }

    @JsonProperty("ReadOnly")
    public boolean getReadOnly() {
        return readOnly;
    }

    public HostVolumeInfo setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static HostVolumeInfo fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, HostVolumeInfo.class);
    }

    public static List<HostVolumeInfo> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, HostVolumeInfo.class);
    }
}
