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
public final class DiskUsage extends ApiObject {
    private long diskMb;
    private long usedMb;

    @JsonProperty("DiskMB")
    public long getDiskMb() {
        return diskMb;
    }

    public DiskUsage setDiskMb(long diskMb) {
        this.diskMb = diskMb;
        return this;
    }

    @JsonProperty("UsedMB")
    public long getUsedMb() {
        return usedMb;
    }

    public DiskUsage setUsedMb(long usedMb) {
        this.usedMb = usedMb;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DiskUsage fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DiskUsage.class);
    }

    public static List<DiskUsage> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DiskUsage.class);
    }
}
