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
public final class NodeReservedDiskResources extends ApiObject {
    private BigInteger diskMb;

    @JsonProperty("DiskMB")
    public BigInteger getDiskMb() {
        return diskMb;
    }

    public NodeReservedDiskResources setDiskMb(BigInteger diskMb) {
        this.diskMb = diskMb;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeReservedDiskResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeReservedDiskResources.class);
    }

    public static List<NodeReservedDiskResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeReservedDiskResources.class);
    }
}
