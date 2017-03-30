package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.testutils.NomadAgentConfiguration;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class RegionsApiTest extends ApiTestBase {

    @Test
    @SuppressWarnings("try")
    public void shouldListRegions() throws Exception {
        try (TestAgent globalAgent = newAgent(new NomadAgentConfiguration.Builder().setServerBootstrapExpect(2))) {
            try (TestAgent fooAgent = newAgent(new NomadAgentConfiguration.Builder().setRegion("foo").setServerStartJoin(globalAgent.getSerfAddress()))) {
                RegionsApi regionsApi = globalAgent.getApiClient().getRegionsApi();

                NomadResponse<List<String>> regionsResponse = regionsApi.list();

                assertThat(regionsResponse.getValue(), contains("foo", "global"));
            }
        }
    }

}
