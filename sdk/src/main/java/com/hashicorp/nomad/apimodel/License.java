package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class License extends ApiObject {
    private String license_id;
    private String customer_id;
    private String installation_id;
    private Date issue_time;
    private Date start_time;
    private Date expiration_time;
    private Date termination_time;
    private String product;
    private Map<String, Object> flags;
    private List<String> modules;
    private List<String> features;

    @JsonProperty("license_id")
    public String getLicense_id() {
        return license_id;
    }

    public License setLicense_id(String license_id) {
        this.license_id = license_id;
        return this;
    }

    @JsonProperty("customer_id")
    public String getCustomer_id() {
        return customer_id;
    }

    public License setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
        return this;
    }

    @JsonProperty("installation_id")
    public String getInstallation_id() {
        return installation_id;
    }

    public License setInstallation_id(String installation_id) {
        this.installation_id = installation_id;
        return this;
    }

    @JsonProperty("issue_time")
    public Date getIssue_time() {
        return issue_time;
    }

    public License setIssue_time(Date issue_time) {
        this.issue_time = issue_time;
        return this;
    }

    @JsonProperty("start_time")
    public Date getStart_time() {
        return start_time;
    }

    public License setStart_time(Date start_time) {
        this.start_time = start_time;
        return this;
    }

    @JsonProperty("expiration_time")
    public Date getExpiration_time() {
        return expiration_time;
    }

    public License setExpiration_time(Date expiration_time) {
        this.expiration_time = expiration_time;
        return this;
    }

    @JsonProperty("termination_time")
    public Date getTermination_time() {
        return termination_time;
    }

    public License setTermination_time(Date termination_time) {
        this.termination_time = termination_time;
        return this;
    }

    @JsonProperty("product")
    public String getProduct() {
        return product;
    }

    public License setProduct(String product) {
        this.product = product;
        return this;
    }

    @JsonProperty("flags")
    public Map<String, Object> getFlags() {
        return flags;
    }

    public License setFlags(Map<String, Object> flags) {
        this.flags = flags;
        return this;
    }

    public License addFlags(String key, Object value) {
        if (this.flags == null)
            this.flags = new java.util.HashMap<>();
        this.flags.put(key, value);
        return this;
    }

    @JsonProperty("modules")
    public List<String> getModules() {
        return modules;
    }

    public License setModules(List<String> modules) {
        this.modules = modules;
        return this;
    }

    public License addModules(String... modules) {
        if (this.modules == null)
            this.modules = new java.util.ArrayList<>();
        for (String item : modules)
            this.modules.add(item);
        return this;
    }

    @JsonProperty("features")
    public List<String> getFeatures() {
        return features;
    }

    public License setFeatures(List<String> features) {
        this.features = features;
        return this;
    }

    public License addFeatures(String... features) {
        if (this.features == null)
            this.features = new java.util.ArrayList<>();
        for (String item : features)
            this.features.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static License fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, License.class);
    }

    public static List<License> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, License.class);
    }
}
