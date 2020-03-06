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
public final class AllocStopResponse extends ApiObject {
    private String evalId;

    @JsonProperty("EvalID")
    public String getEvalId() {
        return evalId;
    }

    public AllocStopResponse setEvalId(String evalId) {
        this.evalId = evalId;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AllocStopResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AllocStopResponse.class);
    }

    public static List<AllocStopResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AllocStopResponse.class);
    }
}
