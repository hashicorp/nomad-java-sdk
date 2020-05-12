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
    private String licenseId;
    private String customerId;
    private String installationId;
    private Date issueTime;
    private Date startTime;
    private Date expirationTime;
    private Date terminationTime;
    private String product;
    private Map<String, Object> flags;
    private List<String> modules;
    private List<String> features;

    @JsonProperty("LicenseID")
    public String getLicenseId() {
        return licenseId;
    }

    public License setLicenseId(String licenseId) {
        this.licenseId = licenseId;
        return this;
    }

    @JsonProperty("CustomerID")
    public String getCustomerId() {
        return customerId;
    }

    public License setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    @JsonProperty("InstallationID")
    public String getInstallationId() {
        return installationId;
    }

    public License setInstallationId(String installationId) {
        this.installationId = installationId;
        return this;
    }

    @JsonProperty("IssueTime")
    public Date getIssueTime() {
        return issueTime;
    }

    public License setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
        return this;
    }

    @JsonProperty("StartTime")
    public Date getStartTime() {
        return startTime;
    }

    public License setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    @JsonProperty("ExpirationTime")
    public Date getExpirationTime() {
        return expirationTime;
    }

    public License setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    @JsonProperty("TerminationTime")
    public Date getTerminationTime() {
        return terminationTime;
    }

    public License setTerminationTime(Date terminationTime) {
        this.terminationTime = terminationTime;
        return this;
    }

    @JsonProperty("Product")
    public String getProduct() {
        return product;
    }

    public License setProduct(String product) {
        this.product = product;
        return this;
    }

    @JsonProperty("Flags")
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

    @JsonProperty("Modules")
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

    @JsonProperty("Features")
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
