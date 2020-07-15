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
public final class DnsConfig extends ApiObject {
    private List<String> servers;
    private List<String> searches;
    private List<String> options;

    @JsonProperty("Servers")
    public List<String> getServers() {
        return servers;
    }

    public DnsConfig setServers(List<String> servers) {
        this.servers = servers;
        return this;
    }

    public DnsConfig addServers(String... servers) {
        if (this.servers == null)
            this.servers = new java.util.ArrayList<>();
        for (String item : servers)
            this.servers.add(item);
        return this;
    }

    @JsonProperty("Searches")
    public List<String> getSearches() {
        return searches;
    }

    public DnsConfig setSearches(List<String> searches) {
        this.searches = searches;
        return this;
    }

    public DnsConfig addSearches(String... searches) {
        if (this.searches == null)
            this.searches = new java.util.ArrayList<>();
        for (String item : searches)
            this.searches.add(item);
        return this;
    }

    @JsonProperty("Options")
    public List<String> getOptions() {
        return options;
    }

    public DnsConfig setOptions(List<String> options) {
        this.options = options;
        return this;
    }

    public DnsConfig addOptions(String... options) {
        if (this.options == null)
            this.options = new java.util.ArrayList<>();
        for (String item : options)
            this.options.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DnsConfig fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DnsConfig.class);
    }

    public static List<DnsConfig> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DnsConfig.class);
    }
}
