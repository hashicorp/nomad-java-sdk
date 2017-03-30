package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.testutils.NomadAgentConfiguration;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StatusApiTest extends ApiTestBase {

    @Test
    public void shouldGetLeader() throws Exception {
        try (TestAgent agent = newServer()) {
            StatusApi statusApi = agent.getApiClient().getStatusApi();

            NomadResponse<String> leaderResponse = statusApi.leader();

            assertThat(leaderResponse.getValue(), is(agent.getRpcAddress()));
        }
    }

    @Test
    public void shouldGetLeaderInOtherRegions() throws Exception {
        try (TestAgent globalAgent = newAgent(new NomadAgentConfiguration.Builder().setServerBootstrapExpect(2))) {
            try (TestAgent fooAgent = newAgent(new NomadAgentConfiguration.Builder().setRegion("foo").setServerStartJoin(globalAgent.getSerfAddress()))) {
                StatusApi statusApi = globalAgent.getApiClient().getStatusApi();

                NomadResponse<String> leaderResponse = statusApi.leader("foo");

                assertThat(leaderResponse.getValue(), is(fooAgent.getRpcAddress()));
            }
        }
    }

    @Test
    public void shouldListPeers() throws Exception {
        try (TestAgent agent = newServer()) {
            StatusApi statusApi = agent.getApiClient().getStatusApi();

            NomadResponse<List<String>> peersResponse = statusApi.peers();

            assertThat(peersResponse.getValue(), contains(agent.getRpcAddress()));
        }
    }

    @Test
    public void shouldListPeersInOtherRegions() throws Exception {
        try (TestAgent globalAgent = newAgent(new NomadAgentConfiguration.Builder().setServerBootstrapExpect(2))) {
            try (TestAgent fooAgent = newAgent(new NomadAgentConfiguration.Builder().setRegion("foo").setServerStartJoin(globalAgent.getSerfAddress()))) {
                StatusApi statusApi = globalAgent.getApiClient().getStatusApi();

                NomadResponse<List<String>> peersResponse = statusApi.peers("foo");

                assertThat(peersResponse.getValue(), contains(fooAgent.getRpcAddress()));
            }
        }
    }
}
