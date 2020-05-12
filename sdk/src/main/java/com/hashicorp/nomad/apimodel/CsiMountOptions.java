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
public final class CsiMountOptions extends ApiObject {
    private String fsType;
    private List<String> mountFlags;

    @JsonProperty("FSType")
    public String getFsType() {
        return fsType;
    }

    public CsiMountOptions setFsType(String fsType) {
        this.fsType = fsType;
        return this;
    }

    @JsonProperty("MountFlags")
    public List<String> getMountFlags() {
        return mountFlags;
    }

    public CsiMountOptions setMountFlags(List<String> mountFlags) {
        this.mountFlags = mountFlags;
        return this;
    }

    public CsiMountOptions addMountFlags(String... mountFlags) {
        if (this.mountFlags == null)
            this.mountFlags = new java.util.ArrayList<>();
        for (String item : mountFlags)
            this.mountFlags.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static CsiMountOptions fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CsiMountOptions.class);
    }

    public static List<CsiMountOptions> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CsiMountOptions.class);
    }
}
