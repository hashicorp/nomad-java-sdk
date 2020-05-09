package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.ScalingPolicy;
import com.hashicorp.nomad.apimodel.ScalingPolicyListStub;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * API for querying for information about scaling policies,
 * exposing the <a href="https://www.nomadproject.io/api-docs/scaling-policies/">scaling policies</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class ScalingApi extends ApiBase {

    ScalingApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Queries a scaling policy.
     *
     * @param id ID of the scaling policy.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/scaling-policies/#read-scaling-policy-beta">{@code GET /v1/scaling/policy/:id}</a>
     */
    public ServerQueryResponse<ScalingPolicy> info(final String id) throws IOException, NomadException {
        return info(id, null);
    }

    /**
     * Queries a scaling policy.
     *
     * @param id    ID of the scaling policy.
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/scaling-policies/#read-scaling-policy-beta">{@code GET /v1/scaling/policy/:id}</a>
     */
    public ServerQueryResponse<ScalingPolicy> info(
            final String id,
            @Nullable final QueryOptions<ScalingPolicy> options
    ) throws IOException, NomadException {
        return executeServerQuery("/v1/scaling/policy/" + id, options, NomadJson.parserFor(ScalingPolicy.class));
    }

    /**
     * Lists all scaling policies.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/scaling-policies/#list-scaling-policies-beta">{@code GET /v1/scaling/policies}</a>
     */
    public ServerQueryResponse<List<ScalingPolicyListStub>> list() throws IOException, NomadException {
        return list(null);
    }

    /**
     * Lists all scaling policies.
     *
     * @param options    options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/scaling-policies/#list-scaling-policies-beta">{@code GET /v1/scaling/policies}</a>
     */
    public ServerQueryResponse<List<ScalingPolicyListStub>> list(
            @Nullable final QueryOptions<List<ScalingPolicyListStub>> options
    ) throws IOException, NomadException {

        return executeServerQuery(
                "/v1/scaling/policies",
                options,
                NomadJson.parserForListOf(ScalingPolicyListStub.class));
    }

}
