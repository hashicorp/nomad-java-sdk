package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AgentHealthResponse;
import com.hashicorp.nomad.apimodel.AgentMember;
import com.hashicorp.nomad.apimodel.AgentSelf;
import com.hashicorp.nomad.apimodel.ServerMembers;
import com.hashicorp.nomad.testutils.NomadAgentConfiguration;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AgentApiTest extends ApiTestBase {

    @Test
    public void shouldReturnSelfInfo() throws Exception {
        try (TestAgent agent = newServer()) {
            AgentApi agentApi = agent.getApiClient().getAgentApi();

            AgentSelf result = agentApi.self().getValue();
            assertThat("Member name in response", result.getMember().getName(), nonEmptyString());
            assertThat((String) result.getConfig().get("Region"), is("global"));
            assertThat((String) result.getConfig().get("Datacenter"), is("dc1"));
        }
    }

    @Test
    public void shouldJoinExistingCluster() throws Exception {
        try (TestAgent bootstrappedServer = newAgent(new NomadAgentConfiguration.Builder().setServerBootstrapExpect(2))) {
            try (TestAgent secondServer = newAgent(new NomadAgentConfiguration.Builder().setServerBootstrapExpect(0))) {
                final AgentApi agentApiForSecondServer = secondServer.getApiClient().getAgentApi();

                new ErrorResponseAssertion("Failed") {
                    @Override
                    protected NomadResponse<?> performRequest() throws IOException, NomadException {
                        return agentApiForSecondServer.join("nope");
                    }
                };

                NomadResponse<Integer> response = agentApiForSecondServer.join(bootstrappedServer.getSerfAddress());
                assertThat(response.getValue(), is(1));
            }
        }
    }

    @Test
    public void shouldListMembers() throws Exception {
        try (TestAgent agent = newServer()) {
            AgentApi agentApi = agent.getApiClient().getAgentApi();

            ServerMembers serverMembers = agentApi.members().getValue();
            assertThat(serverMembers.getServerName(), startsWith("node"));
            assertThat(serverMembers.getServerDc(), is("dc1"));
            assertThat(serverMembers.getServerRegion(), is("global"));
            List<AgentMember> members = serverMembers.getMembers();
            assertThat(members, hasSize(1));
            AgentMember member = members.get(0);
            assertThat("name", member.getName(), nonEmptyString());
            assertThat("addr", member.getAddr(), is("127.0.0.1"));
            assertThat("port", member.getPort(), is((agent.getConfig().getPorts().getSerf())));
        }
    }

    @Test
    public void shouldForceNodeToLeave() throws Exception {
        try (TestAgent agent = newServer()) {
            AgentApi agentApi = agent.getApiClient().getAgentApi();

            ServerMembers initialMembers = agentApi.members().getValue();
            assertThat(initialMembers.getMembers(), hasSize(1));

            NomadResponse<Void> resultOfRequestThatDoesNothing = agentApi.forceLeave("nope");
            assertThat(resultOfRequestThatDoesNothing.getRawEntity(), emptyOrNullString());
        }
    }

    @Test
    public void shouldListServers() throws Exception {
        try (TestAgent agent = newClientServer()) {
            AgentApi agentApi = agent.getApiClient().getAgentApi();

            NomadResponse<List<String>> response = agentApi.servers();
            List<String> servers = response.getValue();
            assertThat("At least one server", servers, contains(nonEmptyString()));
        }
    }

    @Test
    public void shouldUpdateServers() throws Exception {
        try (TestAgent agent = newClientServer()) {
            AgentApi agentApi = agent.getApiClient().getAgentApi();

            TestAgent agent2 = newAgent(new NomadAgentConfiguration.Builder());

            List<String> servers = agentApi.servers().getValue();
            servers.add(agent2.getRpcAddress());
            NomadResponse<Void> result = agentApi.setServers(servers);

            List<String> updatedServers = agentApi.servers().getValue();
            assertThat(updatedServers, equalTo(servers));
        }
    }

    @Test
    public void shouldSupportHealthcheck() throws Exception {
        try (TestAgent agent = newServer()) {
            AgentApi agentApi = agent.getApiClient().getAgentApi();

            AgentHealthResponse health = agentApi.health().getValue();

            assertThat(health.getServer().getOk(), is(true));
            assertThat(health.getClient(), is(nullValue()));
        }
    }

}
