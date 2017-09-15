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
public final class JobValidateResponse extends ApiObject {
    private boolean driverConfigValidated;
    private List<String> validationErrors;
    private String error;
    private String warnings;

    @JsonProperty("DriverConfigValidated")
    public boolean getDriverConfigValidated() {
        return driverConfigValidated;
    }

    public JobValidateResponse setDriverConfigValidated(boolean driverConfigValidated) {
        this.driverConfigValidated = driverConfigValidated;
        return this;
    }

    @JsonProperty("ValidationErrors")
    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public JobValidateResponse setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
        return this;
    }

    public JobValidateResponse addValidationErrors(String... validationErrors) {
        if (this.validationErrors == null)
            this.validationErrors = new java.util.ArrayList<>();
        for (String item : validationErrors)
            this.validationErrors.add(item);
        return this;
    }

    @JsonProperty("Error")
    public String getError() {
        return error;
    }

    public JobValidateResponse setError(String error) {
        this.error = error;
        return this;
    }

    @JsonProperty("Warnings")
    public String getWarnings() {
        return warnings;
    }

    public JobValidateResponse setWarnings(String warnings) {
        this.warnings = warnings;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static JobValidateResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, JobValidateResponse.class);
    }

    public static List<JobValidateResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, JobValidateResponse.class);
    }
}
