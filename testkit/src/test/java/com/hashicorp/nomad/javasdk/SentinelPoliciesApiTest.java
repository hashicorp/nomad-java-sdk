package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AclPolicy;
import com.hashicorp.nomad.apimodel.SentinelPolicy;
import com.hashicorp.nomad.apimodel.SentinelPolicyListStub;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.*;

public class SentinelPoliciesApiTest extends ApiTestBase {

    @Test
    @RequiresNomadEnterprise
    public void shouldBeAbleToListAndUpsertSentinelPolicies() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            SentinelPoliciesApi sentinelPoliciesApi = agent.getApiClient().getSentinelPoliciesApi();

            final ServerQueryResponse<List<SentinelPolicyListStub>> initialListResponse = sentinelPoliciesApi.list();
            assertUpdatedServerResponse(initialListResponse);
            assertThat(initialListResponse.getValue(), is(empty()));

            final SentinelPolicy policy = new SentinelPolicy()
                    .setName("test")
                    .setDescription("test")
                    .setEnforcementLevel("advisory")
                    .setScope("submit-job")
                    .setPolicy("main = rule { true }");

            final ServerResponse<Void> registerResponse = sentinelPoliciesApi.upsert(policy);
            assertUpdatedServerResponse(registerResponse);

            final ServerQueryResponse<List<SentinelPolicyListStub>> sentinelPolicyResponse = sentinelPoliciesApi.list("t");
            assertUpdatedServerResponse(sentinelPolicyResponse);
            assertThat(sentinelPolicyResponse.getValue(), hasSize(1));
            assertThat(sentinelPolicyResponse.getValue().get(0).getName(), is("test"));
        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldBeAbleToDeleteAnSentinelPolicy() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            final SentinelPoliciesApi sentinelPoliciesApi = agent.getApiClient().getSentinelPoliciesApi();

            final SentinelPolicy policy = new SentinelPolicy()
                    .setName("test")
                    .setDescription("test")
                    .setEnforcementLevel("advisory")
                    .setScope("submit-job")
                    .setPolicy("main = rule { true }");

            sentinelPoliciesApi.upsert(policy);

            final ServerResponse<Void> deleteResponse = sentinelPoliciesApi.delete(policy.getName());
            assertUpdatedServerResponse(deleteResponse);

            assertThat(sentinelPoliciesApi.list().getValue(), empty());
        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldBeAbleToRetrieveAnSentinelPolicy() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            final SentinelPoliciesApi sentinelPoliciesApi = agent.getApiClient().getSentinelPoliciesApi();

            final SentinelPolicy policy = new SentinelPolicy()
                    .setName("test")
                    .setDescription("test")
                    .setEnforcementLevel("advisory")
                    .setScope("submit-job")
                    .setPolicy("main = rule { true }");

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return sentinelPoliciesApi.info(policy.getName());
                }
            };

            sentinelPoliciesApi.upsert(policy);

            ServerQueryResponse<SentinelPolicy> retrievedPolicyResponse = sentinelPoliciesApi.info(policy.getName());
            assertUpdatedServerQueryResponse(retrievedPolicyResponse);

            final SentinelPolicy retrievedPolicy = retrievedPolicyResponse.getValue();
            assertThat(retrievedPolicy.getName(), is(policy.getName()));
            assertThat(retrievedPolicy.getDescription(), is(policy.getDescription()));
            assertThat(retrievedPolicy.getPolicy(), is(policy.getPolicy()));
        }
    }

}
