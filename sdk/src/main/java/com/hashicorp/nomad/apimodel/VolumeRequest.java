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
public final class VolumeRequest extends ApiObject {
    private String name;
    private String type;
    private String source;
    private boolean readOnly;
    private CsiMountOptions mountOptions;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public VolumeRequest setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public VolumeRequest setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Source")
    public String getSource() {
        return source;
    }

    public VolumeRequest setSource(String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("ReadOnly")
    public boolean getReadOnly() {
        return readOnly;
    }

    public VolumeRequest setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    @JsonProperty("MountOptions")
    public CsiMountOptions getMountOptions() {
        return mountOptions;
    }

    public VolumeRequest setMountOptions(CsiMountOptions mountOptions) {
        this.mountOptions = mountOptions;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static VolumeRequest fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, VolumeRequest.class);
    }

    public static List<VolumeRequest> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, VolumeRequest.class);
    }
}
