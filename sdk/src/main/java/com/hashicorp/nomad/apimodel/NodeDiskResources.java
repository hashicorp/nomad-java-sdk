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
public final class NodeDiskResources extends ApiObject {
    private long diskMb;

    @JsonProperty("DiskMB")
    public long getDiskMb() {
        return diskMb;
    }

    public NodeDiskResources setDiskMb(long diskMb) {
        this.diskMb = diskMb;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeDiskResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeDiskResources.class);
    }

    public static List<NodeDiskResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeDiskResources.class);
    }
}
