package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.SentinelPolicy;
import com.hashicorp.nomad.apimodel.SentinelPolicyListStub;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * API for managing sentinel policies,
 * exposing the <a href="https://www.nomadproject.io/api/sentinel-policies.html">sentinel policies</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class SentinelPoliciesApi extends ApiBase {

    SentinelPoliciesApi(NomadApiClient apiClient) {
        super(apiClient);
    }


    /**
     * Deletes an sentinel policy.
     *
     * @param name name of the policy to delete
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/sentinel-policies.html#delete-policy">{@code DELETE /v1/sentinel/policy/:name}</a>
     */
    public ServerResponse<Void> delete(final String name) throws IOException, NomadException {
        return delete(name, null);
    }

    /**
     * Deletes an sentinel policy.
     *
     * @param policyName name of the policy to delete
     * @param options    options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/sentinel-policies.html#delete-policy">{@code DELETE /v1/sentinel/policy/:name}</a>
     */
    public ServerResponse<Void> delete(final String policyName, @Nullable final WriteOptions options)
            throws IOException, NomadException {

        if (policyName.isEmpty()) {
            throw new IllegalArgumentException("Policy name must be a non-empty string");
        }

        return executeServerAction(delete(uri("/v1/sentinel/policy/" + policyName), options), null);
    }

    /**
     * Retrieves an sentinel policy.
     *
     * @param name name of the policy.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/sentinel-policies.html#read-policy">{@code GET /v1/sentinel/policy/:name}</a>
     */
    public ServerQueryResponse<SentinelPolicy> info(final String name) throws IOException, NomadException {
        return info(name, null);
    }

    /**
     * Retrieves an sentinel policy.
     *
     * @param name    name of the policy.
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/sentinel-policies.html#read-policy">{@code GET /v1/sentinel/policy/:name}</a>
     */
    public ServerQueryResponse<SentinelPolicy> info(
            final String name,
            @Nullable final QueryOptions<SentinelPolicy> options
    ) throws IOException, NomadException {

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Policy name must be a non-empty string");
        }

        return executeServerQuery("/v1/sentinel/policy/" + name, options, NomadJson.parserFor(SentinelPolicy.class));
    }

    /**
     * Lists all sentinel policies.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/sentinel-policies.html#list-policies">{@code GET /v1/sentinel/policies}</a>
     */
    public ServerQueryResponse<List<SentinelPolicyListStub>> list() throws IOException, NomadException {
        return list(null, null);
    }

    /**
     * Lists all sentinel policies.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/sentinel-policies.html#list-policies">{@code GET /v1/sentinel/policies}</a>
     */
    public ServerQueryResponse<List<SentinelPolicyListStub>> list(
            @Nullable final QueryOptions<List<SentinelPolicyListStub>> options
    ) throws IOException, NomadException {

        return list(null, options);
    }

    /**
     * Lists sentinel policies.
     *
     * @param namePrefix a name prefix that, if given,
     *                   restricts the results to only policies having a name with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/sentinel-policies.html#list-policies">{@code GET /v1/sentinel/policies}</a>
     */
    public ServerQueryResponse<List<SentinelPolicyListStub>> list(@Nullable final String namePrefix)
            throws IOException, NomadException {

        return list(namePrefix, null);
    }

    /**
     * Lists sentinel policies.
     *
     * @param namePrefix a name prefix that, if given,
     *                   restricts the results to only policies having a name with this prefix
     * @param options    options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/sentinel-policies.html#list-policies">{@code GET /v1/sentinel/policies}</a>
     */
    public ServerQueryResponse<List<SentinelPolicyListStub>> list(
            @Nullable final String namePrefix,
            @Nullable final QueryOptions<List<SentinelPolicyListStub>> options
    ) throws IOException, NomadException {

        return executeServerQueryForPrefixFilteredList(
                "/v1/sentinel/policies",
                namePrefix,
                options,
                NomadJson.parserForListOf(SentinelPolicyListStub.class));
    }

    /**
     * Creates or updates an sentinel policy.
     *
     * @param policy the policy
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/sentinel-policies.html#create-or-update-policy">{@code PUT /v1/sentinel/policy/:name}</a>
     */
    public ServerResponse<Void> upsert(SentinelPolicy policy) throws IOException, NomadException {
        return upsert(policy, null);
    }

    /**
     * Creates or updates an sentinel policy.
     *
     * @param policy  the policy
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/sentinel-policies.html#create-or-update-policy">{@code PUT /v1/sentinel/policy/:name}</a>
     */
    public ServerResponse<Void> upsert(SentinelPolicy policy, @Nullable WriteOptions options)
            throws IOException, NomadException {

        if (policy.getName() == null || policy.getName().isEmpty()) {
            throw new IllegalArgumentException("Cannot upsert policy that does not have a name");
        }

        return executeServerAction(
                put("/v1/sentinel/policy/" + policy.getName(), policy, options),
                null);
    }

}
