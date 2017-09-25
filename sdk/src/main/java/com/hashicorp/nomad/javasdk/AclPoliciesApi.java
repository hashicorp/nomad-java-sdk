package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AclPolicy;
import com.hashicorp.nomad.apimodel.AclPolicyListStub;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * API for managing ACL policies,
 * exposing the <a href="https://www.nomadproject.io/api/acl-policies.html">ACL policies</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class AclPoliciesApi extends ApiBase {

    AclPoliciesApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Deletes an ACL policy.
     *
     * @param policyName name of the policy to delete
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-policy.html#delete-policy">{@code DELETE /v1/acl/policy/:name}</a>
     */
    public ServerResponse<Void> delete(final String policyName) throws IOException, NomadException {
        return delete(policyName, null);
    }

    /**
     * Deletes an ACL policy.
     *
     * @param policyName name of the policy to delete
     * @param options    options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-policy.html#delete-policy">{@code DELETE /v1/acl/policy/:name}</a>
     */
    public ServerResponse<Void> delete(final String policyName, @Nullable final WriteOptions options)
            throws IOException, NomadException {

        if (policyName.isEmpty()) {
            throw new IllegalArgumentException("Policy name must be a non-empty string");
        }

        return executeServerAction(delete(uri("/v1/acl/policy/" + policyName), options), null);
    }

    /**
     * Retrieves an ACL policy.
     *
     * @param name name of the policy.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-policies.html#read-policy">{@code GET /v1/acl/policy/:name}</a>
     */
    public ServerQueryResponse<AclPolicy> info(final String name) throws IOException, NomadException {
        return info(name, null);
    }

    /**
     * Retrieves an ACL policy.
     *
     * @param name    name of the policy.
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-policies.html#read-policy">{@code GET /v1/acl/policy/:name}</a>
     */
    public ServerQueryResponse<AclPolicy> info(
            final String name,
            @Nullable final QueryOptions<AclPolicy> options
    ) throws IOException, NomadException {
        return executeServerQuery("/v1/acl/policy/" + name, options, NomadJson.parserFor(AclPolicy.class));
    }

    /**
     * Lists all ACL policies.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-policies.html#list-policies">{@code GET /v1/acl/policies}</a>
     */
    public ServerQueryResponse<List<AclPolicyListStub>> list() throws IOException, NomadException {
        return list(null, null);
    }

    /**
     * Lists all ACL policies.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-policies.html#list-policies">{@code GET /v1/acl/policies}</a>
     */
    public ServerQueryResponse<List<AclPolicyListStub>> list(
            @Nullable final QueryOptions<List<AclPolicyListStub>> options
    ) throws IOException, NomadException {

        return list(null, options);
    }

    /**
     * Lists ACL policies.
     *
     * @param namePrefix a name prefix that, if given,
     *                   restricts the results to only policies having a name with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-policies.html#list-policies">{@code GET /v1/acl/policies}</a>
     */
    public ServerQueryResponse<List<AclPolicyListStub>> list(@Nullable final String namePrefix)
            throws IOException, NomadException {

        return list(namePrefix, null);
    }

    /**
     * Lists ACL policies.
     *
     * @param namePrefix a name prefix that, if given,
     *                   restricts the results to only policies having a name with this prefix
     * @param options    options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-policies.html#list-policies">{@code GET /v1/acl/policies}</a>
     */
    public ServerQueryResponse<List<AclPolicyListStub>> list(
            @Nullable final String namePrefix,
            @Nullable final QueryOptions<List<AclPolicyListStub>> options
    ) throws IOException, NomadException {

        return executeServerQueryForPrefixFilteredList(
                "/v1/acl/policies",
                namePrefix,
                options,
                NomadJson.parserForListOf(AclPolicyListStub.class));
    }

    /**
     * Creates or updates an ACL policy.
     *
     * @param policy the ACL policy
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-policies.html#create-or-update-policy">{@code PUT /v1/acl/policy/:name}</a>
     */
    public ServerResponse<Void> upsert(AclPolicy policy) throws IOException, NomadException {
        return upsert(policy, null);
    }

    /**
     * Creates or updates an ACL policy.
     *
     * @param policy  the ACL policy
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-policies.html#create-or-update-policy">{@code PUT /v1/acl/policy/:name}</a>
     */
    public ServerResponse<Void> upsert(AclPolicy policy, @Nullable WriteOptions options)
            throws IOException, NomadException {

        if (policy.getName() == null || policy.getName().isEmpty()) {
            throw new IllegalArgumentException("Cannot upsert policy that does not have a name");
        }

        return executeServerAction(
                put("/v1/acl/policy/" + policy.getName(), policy, options),
                null);
    }

}
