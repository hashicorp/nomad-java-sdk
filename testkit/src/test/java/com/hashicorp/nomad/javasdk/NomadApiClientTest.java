package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AclToken;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NomadApiClientTest extends ApiTestBase {

    @Test
    public void shouldAllowRequestLevelTokenOverride() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            final AclTokensApi tokensApi = agent.getApiClient().getAclTokensApi();
            final AclToken origSelfToken = tokensApi.self().getValue();
            agent.getApiClient().setAuthToken(UUID.randomUUID().toString());

            new ErrorResponseAssertion(403) {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return tokensApi.info(origSelfToken.getAccessorId());
                }
            };

            ServerQueryResponse<AclToken> qrySelfToken = tokensApi.self(new QueryOptions<AclToken>().setAuthToken(origSelfToken.getSecretId()));
            assertThat(qrySelfToken.getValue().getAccessorId(), is(origSelfToken.getAccessorId()));
        }
    }

    @Test
    public void shouldPreferRequestLevelToken() throws Exception {
        try (TestAgent agent = newAclBootstrappedServer()) {
            final AclTokensApi tokensApi = agent.getApiClient().getAclTokensApi();
            final AclToken origSelfToken = tokensApi.self().getValue();

            new ErrorResponseAssertion(403) {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return tokensApi.info(origSelfToken.getAccessorId(), new QueryOptions<AclToken>().setAuthToken(UUID.randomUUID().toString()));
                }
            };
        }
    }

}
