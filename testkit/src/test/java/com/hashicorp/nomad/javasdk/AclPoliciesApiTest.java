package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AclPolicy;
import com.hashicorp.nomad.apimodel.AclPolicyListStub;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AclPoliciesApiTest extends ApiTestBase {

    @Test
    public void shouldBeAbleToListAndUpsertAclPolicies() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            AclPoliciesApi aclPoliciesApi = agent.getApiClient().getAclPoliciesApi();

            final ServerQueryResponse<List<AclPolicyListStub>> initialListResponse = aclPoliciesApi.list();
            assertUpdatedServerResponse(initialListResponse);
            assertThat(initialListResponse.getValue(), is(empty()));

            final AclPolicy aclPolicy = new AclPolicy()
                    .setName("test")
                    .setDescription("test")
                    .setRules("namespace \"default\" { policy = \"read\" }");

            final ServerResponse<Void> registerResponse = aclPoliciesApi.upsert(aclPolicy);
            assertUpdatedServerResponse(registerResponse);

            final ServerQueryResponse<List<AclPolicyListStub>> aclPolicyResponse = aclPoliciesApi.list();
            assertUpdatedServerResponse(aclPolicyResponse);
            assertThat(aclPolicyResponse.getValue(), hasSize(1));
            assertThat(aclPolicyResponse.getValue().get(0).getName(), is("test"));
        }
    }

    @Test
    public void shouldBeAbleToDeleteAnAclPolicy() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            final AclPoliciesApi aclPoliciesApi = agent.getApiClient().getAclPoliciesApi();

            final AclPolicy policy = new AclPolicy()
                    .setName("test")
                    .setDescription("Test Policy")
                    .setRules("namespace \"default\" { policy = \"read\" }");

            aclPoliciesApi.upsert(policy);

            final ServerResponse<Void> deleteResponse = aclPoliciesApi.delete(policy.getName());
            assertUpdatedServerResponse(deleteResponse);

            assertThat(aclPoliciesApi.list().getValue(), empty());
        }
    }

    @Test
    public void shouldBeAbleToRetrieveAnAclPolicy() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            final AclPoliciesApi aclPoliciesApi = agent.getApiClient().getAclPoliciesApi();

            final AclPolicy policy = new AclPolicy()
                    .setName("test")
                    .setDescription("Test Policy")
                    .setRules("namespace \"default\" { policy = \"read\" }");

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return aclPoliciesApi.info(policy.getName());
                }
            };

            aclPoliciesApi.upsert(policy);

            ServerQueryResponse<AclPolicy> retrievedPolicyResponse = aclPoliciesApi.info(policy.getName());
            assertUpdatedServerQueryResponse(retrievedPolicyResponse);

            final AclPolicy retrievedPolicy = retrievedPolicyResponse.getValue();
            assertThat(retrievedPolicy.getName(), is(policy.getName()));
            assertThat(retrievedPolicy.getDescription(), is(policy.getDescription()));
            assertThat(retrievedPolicy.getRules(), is(policy.getRules()));
        }
    }

}
