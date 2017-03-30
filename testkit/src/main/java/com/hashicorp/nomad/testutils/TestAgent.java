package com.hashicorp.nomad.testutils;

import com.hashicorp.nomad.javasdk.NomadApiClient;
import com.hashicorp.nomad.javasdk.NomadApiConfiguration;
import org.apache.http.HttpHost;

import static com.hashicorp.nomad.javasdk.WaitStrategy.waitForMilliseconds;

/**
 * AutoClosable combination of Nomad agent and API client, for easy resource cleanup in tests.
 */
public class TestAgent implements AutoCloseable {

    private final NomadAgentProcess agentProcess;
    private final NomadApiClient apiClient;

    /**
     * Creates a new TestAgent and waits until the agent is ready.
     *
     * @throws Exception if there is a problem while waiting for the agent to be ready
     */
    public TestAgent(NomadAgentProcess agentProcess, NomadApiClient apiClient) throws Exception {
        this.agentProcess = agentProcess;
        this.apiClient = apiClient;

        agentProcess.pollUntilReady(apiClient, waitForMilliseconds(20000, 200));
    }

    /**
     * Creates a new TestAgent and waits until the agent is ready.
     *
     * @throws Exception if there is a problem while waiting for the agent to be ready
     */
    public TestAgent(NomadAgentProcess agentProcess) throws Exception {
        this(agentProcess, new NomadApiClient(agentProcess.getHttpAddress()));
    }

    /**
     * Creates a new TestAgent and waits until the agent is ready.
     *
     * @throws Exception if there is a problem while waiting for the agent to be ready
     */
    public TestAgent(NomadAgentProcess agentProcess,
                     NomadApiConfiguration.Builder apiConfigBuilder) throws Exception {
        this(agentProcess, new NomadApiClient(apiConfigBuilder.setAddress(agentProcess.getHttpAddress()).build()));
    }

    /**
     * Returns the api client.
     */
    public NomadApiClient getApiClient() {
        return apiClient;
    }

    /**
     * Returns the agent's configuration.
     */
    public NomadAgentConfiguration getConfig() {
        return agentProcess.getConfig();
    }

    /**
     * Returns the HTTP or HTTPS address (meaning "scheme://host:port") of the agent.
     */
    public HttpHost getHttpAddress() {
        return agentProcess.getHttpAddress();
    }

    /**
     * Returns the RPC address (meaning "host:port") of the agent.
     */
    public String getRpcAddress() {
        return agentProcess.getRpcAddress();
    }

    /**
     * Returns the Serf address (meaning "host:port") of the agent.
     */
    public String getSerfAddress() {
        return agentProcess.getSerfAddress();
    }

    @Override
    public void close() throws Exception {
        try {
            agentProcess.close();
        } finally {
            apiClient.close();
        }
    }
}
