package com.hashicorp.nomad.testutils;

import com.hashicorp.nomad.javasdk.NomadApiClient;
import com.hashicorp.nomad.javasdk.NomadApiConfiguration;
import com.hashicorp.nomad.javasdk.NomadException;
import org.apache.http.HttpHost;

import java.io.IOException;

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
     * @param agentProcess the agent process
     * @param apiClient    the API client to use
     * @throws Exception if there is a problem while waiting for the agent to be ready
     */
    public TestAgent(NomadAgentProcess agentProcess, NomadApiClient apiClient) {
        this.agentProcess = agentProcess;
        this.apiClient = apiClient;
    }

    /**
     * Creates a new TestAgent.
     *
     * @param agentProcess the agent process
     * @throws Exception if there is a problem while waiting for the agent to be ready
     */
    public TestAgent(NomadAgentProcess agentProcess) {
        this(agentProcess, new NomadApiClient(agentProcess.getHttpAddress()));
    }

    /**
     * Creates a new TestAgent and waits until the agent is ready.
     *
     * @param agentProcess     the agent process
     * @param apiConfigBuilder a partially-configured API config builder
     * @throws Exception if there is a problem while waiting for the agent to be ready
     */
    public TestAgent(NomadAgentProcess agentProcess,
                     NomadApiConfiguration.Builder apiConfigBuilder) {
        this(agentProcess, new NomadApiClient(apiConfigBuilder.setAddress(agentProcess.getHttpAddress()).build()));
    }

    /**
     * Polls untill the agent is ready.
     *
     * @throws IOException          if there is an IO problem
     * @throws NomadException       if something is wrong with Nomad
     * @throws InterruptedException if interrupted
     */
    public void pollUntilReady() throws IOException, NomadException, InterruptedException {
        agentProcess.pollUntilReady(apiClient, waitForMilliseconds(20000, 200));
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
    public void close() throws IOException {
        try {
            agentProcess.close();
        } finally {
            apiClient.close();
        }
    }
}
