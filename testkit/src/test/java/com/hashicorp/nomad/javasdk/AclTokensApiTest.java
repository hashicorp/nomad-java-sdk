package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AclToken;
import com.hashicorp.nomad.apimodel.AclTokenListStub;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

public class AclTokensApiTest extends ApiTestBase {

    @Test
    public void shouldBeAbleToListTokens() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            AclTokensApi aclTokensApi = agent.getApiClient().getAclTokensApi();

            final ServerQueryResponse<List<AclTokenListStub>> response = aclTokensApi.list();
            assertUpdatedServerResponse(response);

            final List<AclTokenListStub> tokens = response.getValue();
            assertThat("Expected to find the bootstrap token", tokens, hasSize(1));
            assertThat(tokens.get(0).getAccessorId(), nonEmptyString());
            assertThat(tokens.get(0).getName(), is("Bootstrap Token"));
            assertThat(tokens.get(0).getType(), is("management"));
        }
    }

    @Test
    public void shouldBeAbleToCreateAndUpdateTokens() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            AclTokensApi aclTokensApi = agent.getApiClient().getAclTokensApi();

            final AclToken token = new AclToken()
                    .setName("foo")
                    .setType("client")
                    .setPolicies(asList("foo-policy"));

            final ServerResponse<AclToken> createResponse = aclTokensApi.create(token);
            assertUpdatedServerResponse(createResponse);
            final AclToken createdToken = createResponse.getValue();

            assertThat(createdToken.getAccessorId(), nonEmptyString());

            createdToken.setName("bar");

            final ServerResponse<AclToken> updatedTokenResponse = aclTokensApi.update(createdToken);
            assertUpdatedServerResponse(updatedTokenResponse);
            final AclToken updatedToken = updatedTokenResponse.getValue();

            assertThat(updatedToken.getAccessorId(), is(createdToken.getAccessorId()));
            assertThat(updatedToken.getName(), is("bar"));
            assertThat(updatedToken.getPolicies(), is(asList("foo-policy")));
        }
    }

    @Test
    public void shouldBeAbleToRetrieveAToken() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            final AclTokensApi aclTokensApi = agent.getApiClient().getAclTokensApi();

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return aclTokensApi.info(UUID.randomUUID().toString());
                }
            };

            final AclToken token = new AclToken()
                    .setName("foo")
                    .setType("client")
                    .setPolicies(asList("foo-policy"));

            final AclToken createdToken = aclTokensApi.create(token).getValue();

            ServerQueryResponse<AclToken> infoResponse = aclTokensApi.info(createdToken.getAccessorId());
            assertUpdatedServerQueryResponse(infoResponse);
            assertThat(infoResponse.getValue(), samePropertyValuesAs(createdToken));
        }
    }

    @Test
    public void shouldBeAbleToListOwnTokens() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            final AclTokensApi aclTokensApi = agent.getApiClient().getAclTokensApi();

            final AclToken token = new AclToken()
                    .setName("test")
                    .setType("client")
                    .setPolicies(asList("foo-policy"));

            final AclToken createdToken = aclTokensApi.create(token).getValue();

            agent.getApiClient().setAuthToken(createdToken.getSecretId());

            final ServerQueryResponse<AclToken> selfResponse = aclTokensApi.self();
            assertUpdatedServerResponse(selfResponse);

            assertThat(selfResponse.getValue(), samePropertyValuesAs(createdToken));
        }
    }

    @Test
    public void shouldBeAbleToDeleteAToken() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            final AclTokensApi aclTokensApi = agent.getApiClient().getAclTokensApi();

            final AclToken token = new AclToken()
                    .setName("test")
                    .setType("client")
                    .setPolicies(asList("foo-policy"));

            final String accessorId = aclTokensApi.create(token).getValue().getAccessorId();

            final ServerResponse<Void> deleteResponse = aclTokensApi.delete(accessorId);
            assertUpdatedServerResponse(deleteResponse);

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return aclTokensApi.info(accessorId);
                }
            };
        }
    }

}
