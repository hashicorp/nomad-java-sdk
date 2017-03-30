package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class Vault {
    private List<String> policies;
    private Boolean env;
    private String changeMode;
    private String changeSignal;

    @JsonProperty("Policies")
    public List<String> getPolicies() {
        return policies;
    }

    public Vault setPolicies(List<String> policies) {
        this.policies = policies;
        return this;
    }

    public Vault addPolicies(String... policies) {
        if (this.policies == null)
            this.policies = new java.util.ArrayList<>();
        for (String item : policies)
            this.policies.add(item);
        return this;
    }

    @JsonProperty("Env")
    public Boolean getEnv() {
        return env;
    }

    public Vault setEnv(Boolean env) {
        this.env = env;
        return this;
    }

    @JsonProperty("ChangeMode")
    public String getChangeMode() {
        return changeMode;
    }

    public Vault setChangeMode(String changeMode) {
        this.changeMode = changeMode;
        return this;
    }

    @JsonProperty("ChangeSignal")
    public String getChangeSignal() {
        return changeSignal;
    }

    public Vault setChangeSignal(String changeSignal) {
        this.changeSignal = changeSignal;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Vault fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Vault.class);
    }

    public static List<Vault> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Vault.class);
    }
}
