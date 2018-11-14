package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AutopilotConfiguration;
import com.hashicorp.nomad.apimodel.OperatorHealthReply;
import com.hashicorp.nomad.apimodel.RaftConfiguration;
import com.hashicorp.nomad.apimodel.SchedulerConfiguration;
import com.hashicorp.nomad.testutils.NomadAgentConfiguration;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OperatorApiTest extends ApiTestBase {

    @Test
    public void shouldGetRaftConfiguration() throws Exception {
        try (TestAgent agent = newServer()) {
            final OperatorApi operatorApi = agent.getApiClient().getOperatorApi();

            final NomadResponse<RaftConfiguration> response = operatorApi.raftGetConfiguration();
            final RaftConfiguration raft = response.getValue();
            assertThat("servers", raft.getServers(), hasSize(1));
            assertThat("leader", raft.getServers().get(0).getLeader(), is(true));
            assertThat("votor", raft.getServers().get(0).getVoter(), is(true));
        }
    }

    @Test
    public void shouldErrorWhenTryingToRemoveNonExistentRaftPeer() throws Exception {
        try (TestAgent agent = newServer()) {
            final OperatorApi operatorApi = agent.getApiClient().getOperatorApi();

            new ErrorResponseAssertion("address \"no-one-here\" was not found in the Raft configuration") {
                @Override
                protected NomadResponse<Void> performRequest() throws IOException, NomadException {
                    return operatorApi.raftRemovePeerByAddress("no-one-here");
                }
            };
        }
    }

    @Test
    public void shouldGetOperatorHealthAs429OnStartup() throws Exception {
        try (TestAgent agent = newAgent(new NomadAgentConfiguration.Builder().setRaftProtocol(3))) {
            final OperatorApi operatorApi = agent.getApiClient().getOperatorApi();

            new ErrorResponseAssertion(429) {
                @Override
                protected NomadResponse<OperatorHealthReply> performRequest() throws IOException, NomadException {
                    return operatorApi.getHealth();
                }
            };
        }
    }

    @Test
    public void shouldGetOperatorHealth() throws Exception {
        try (TestAgent agent = newAgent(new NomadAgentConfiguration.Builder().setRaftProtocol(3))) {
            final OperatorApi operatorApi = agent.getApiClient().getOperatorApi();

            agent.getApiClient().pollUntilClusterHealthy(waitStrategyForTest());

            final NomadResponse<OperatorHealthReply> response = operatorApi.getHealth();
            final OperatorHealthReply health = response.getValue();
            assertThat("healthy", health.getHealthy(), is(true));
            assertThat("servers", health.getServers(), hasSize(1));
            assertThat("failureTolerance", health.getFailureTolerance(), is(0));
        }
    }

    @Test
    public void shouldGetAutopilotConfiguration() throws Exception {
        try (TestAgent agent = newAgent(new NomadAgentConfiguration.Builder().setRaftProtocol(3))) {
            final OperatorApi operatorApi = agent.getApiClient().getOperatorApi();

            NomadResponse<AutopilotConfiguration> autopilotConfiguration = operatorApi.getAutopilotConfiguration();
            assertThat(autopilotConfiguration.getValue().getCleanupDeadServers(), is(true));
        }
    }

    @Test
    public void canSetAutopilotConfiguration() throws Exception {
        try (TestAgent agent = newAgent(new NomadAgentConfiguration.Builder().setRaftProtocol(3))) {
            final OperatorApi operatorApi = agent.getApiClient().getOperatorApi();

            AutopilotConfiguration autopilotConfiguration = operatorApi.getAutopilotConfiguration().getValue();
            assertThat(autopilotConfiguration.getCleanupDeadServers(), is(true));
            autopilotConfiguration.setCleanupDeadServers(false);

            NomadResponse<Boolean> response = operatorApi.updateAutopilotConfiguration(autopilotConfiguration);
            assertThat(response.getValue(), is(true));

            AutopilotConfiguration updatedAutopilotConfiguration = operatorApi.getAutopilotConfiguration().getValue();
            assertThat(updatedAutopilotConfiguration.getCleanupDeadServers(), is(false));
        }
    }

    @Test
    public void supportCheckAndSetForAutopilotConfiguration() throws Exception {
        try (TestAgent agent = newAgent(new NomadAgentConfiguration.Builder().setRaftProtocol(3))) {
            final OperatorApi operatorApi = agent.getApiClient().getOperatorApi();

            final AutopilotConfiguration autopilotConfiguration = operatorApi.getAutopilotConfiguration().getValue();
            assertThat(autopilotConfiguration.getCleanupDeadServers(), is(true));
            autopilotConfiguration.setCleanupDeadServers(false);

            NomadResponse<Boolean> failedResponse = operatorApi.updateAutopilotConfiguration(autopilotConfiguration, null, autopilotConfiguration.getModifyIndex().add(BigInteger.ONE));
            assertThat(failedResponse.getValue(), is(false));

            NomadResponse<Boolean> response = operatorApi.updateAutopilotConfiguration(autopilotConfiguration, null, autopilotConfiguration.getModifyIndex());
            assertThat(response.getValue(), is(true));

            AutopilotConfiguration updatedAutopilotConfiguration = operatorApi.getAutopilotConfiguration().getValue();
            assertThat(updatedAutopilotConfiguration.getCleanupDeadServers(), is(false));
        }
    }

    @Test
    public void shouldGetSchedulerConfiguration() throws Exception {
        try (TestAgent agent = newServer()) {
            final OperatorApi operatorApi = agent.getApiClient().getOperatorApi();

            final NomadResponse<SchedulerConfiguration> response = operatorApi.getSchedulerConfiguration(null);
            final SchedulerConfiguration config = response.getValue();
            assertThat("systemScheduler", config.getPreemptionConfig().getSystemSchedulerEnabled(), is(true));
        }
    }

    @Test
    public void canSetSchedulerConfiguration() throws Exception {
        try (TestAgent agent = newAgent(new NomadAgentConfiguration.Builder().setRaftProtocol(3))) {
            final OperatorApi operatorApi = agent.getApiClient().getOperatorApi();

            SchedulerConfiguration schedulerConfiguration = operatorApi.getSchedulerConfiguration(null).getValue();
            assertThat(schedulerConfiguration.getPreemptionConfig().getSystemSchedulerEnabled(), is(true));

            schedulerConfiguration.getPreemptionConfig().setSystemSchedulerEnabled(false);
            NomadResponse<Boolean> response = operatorApi.updateSchedulerConfiguration(schedulerConfiguration);
            assertThat(response.getValue(), is(true));

            SchedulerConfiguration updatedSchedulerConfiguration = operatorApi.getSchedulerConfiguration(null).getValue();
            assertThat(updatedSchedulerConfiguration.getPreemptionConfig().getSystemSchedulerEnabled(), is(false));
        }
    }

    @Test
    public void supportCheckAndSetForSchedulerConfiguration() throws Exception {
        try (TestAgent agent = newAgent(new NomadAgentConfiguration.Builder().setRaftProtocol(3))) {
            final OperatorApi operatorApi = agent.getApiClient().getOperatorApi();

            final SchedulerConfiguration schedulerConfiguration = operatorApi.getSchedulerConfiguration(null).getValue();
            assertThat(schedulerConfiguration.getPreemptionConfig().getSystemSchedulerEnabled(), is(true));

            schedulerConfiguration.getPreemptionConfig().setSystemSchedulerEnabled(false);
            NomadResponse<Boolean> failedResponse = operatorApi.updateSchedulerConfiguration(schedulerConfiguration, null, schedulerConfiguration.getModifyIndex().add(BigInteger.ONE));
            assertThat(failedResponse.getValue(), is(false));

            NomadResponse<Boolean> response = operatorApi.updateSchedulerConfiguration(schedulerConfiguration, null, schedulerConfiguration.getModifyIndex());
            assertThat(response.getValue(), is(true));

            SchedulerConfiguration updatedSchedulerConfiguration = operatorApi.getSchedulerConfiguration(null).getValue();
            assertThat(updatedSchedulerConfiguration.getPreemptionConfig().getSystemSchedulerEnabled(), is(false));
        }
    }

}
