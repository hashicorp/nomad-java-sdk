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
public final class LicenseReply extends ApiObject {
    private License license;

    @JsonProperty("License")
    public License getLicense() {
        return license;
    }

    public LicenseReply setLicense(License license) {
        this.license = license;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static LicenseReply fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, LicenseReply.class);
    }

    public static List<LicenseReply> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, LicenseReply.class);
    }
}
