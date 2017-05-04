package com.hashicorp.nomad.testutils;

import com.hashicorp.nomad.javasdk.NomadApiClient;
import com.hashicorp.nomad.javasdk.NomadException;
import com.hashicorp.nomad.javasdk.NomadResponse;
import com.hashicorp.nomad.javasdk.WaitStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.hashicorp.nomad.javasdk.WaitStrategy.whileProcessIsRunning;

/**
 * Runs and encapsulates a Nomad agent process to test against.
 */
public class NomadAgentProcess implements AutoCloseable {

    private final NomadAgentConfiguration config;
    private final Path configFile;
    private final Path managedDataDir;
    private final AugmentedProcess process;

    /**
     * Runs a new Nomad agent.
     *
     * @param output a PrintWriter to which the process output is written
     * @param config the configuration to use to run the agent
     */
    public NomadAgentProcess(PrintWriter output, NomadAgentConfiguration config) {
        this(output, config, "nomad");
    }

    /**
     * Runs a new Nomad agent.
     *
     * @param output a PrintWriter to which the process output is written
     * @param config the configuration to use to run the agent
     * @param executable the Nomad executable to run
     */
    public NomadAgentProcess(PrintWriter output, NomadAgentConfiguration config, String executable) {
        try {
            this.config = config;

            configFile = Files.createTempFile("nomad", "json");
            FileUtils.writeStringToFile(configFile.toFile(), config.asJson(), "UTF-8");

            List<String> command = new ArrayList<>();

            command.add(executable);
            command.add("agent");
            command.add("-config");
            command.add(configFile.toString());

            if (config.getDataDir() == null) {
                managedDataDir = Files.createTempDirectory("nomad");
                command.add("-data-dir");
                command.add(managedDataDir.toString());
            } else {
                managedDataDir = null;
            }

            output.println("Running: " + StringUtils.join(command.iterator(), " "));
            process = new AugmentedProcess(command, output);
        } catch (Throwable t) {
            final RuntimeException wrapped =
                    new RuntimeException("Error during test agent initialization: " + t.getMessage(), t);
            try {
                close();
            } catch (Throwable t2) {
                wrapped.addSuppressed(t2);
            }
            throw wrapped;
        }
    }

    /**
     * Returns the agent's configuration.
     */
    public NomadAgentConfiguration getConfig() {
        return config;
    }

    /**
     * Returns the HTTP or HTTPS address (meaning "scheme://host:port") of the agent.
     */
    public HttpHost getHttpAddress() {
        return new HttpHost(getIpAddress(), config.getPorts().getHttp(), config.getTls().isHttp() ? "https" : "http");
    }

    /**
     * Returns the RPC address (meaning "host:port") of the agent.
     */
    public String getRpcAddress() {
        return getIpAddress() + ":" + config.getPorts().getRpc();
    }

    /**
     * Returns the Serf address (meaning "host:port") of the agent.
     */
    public String getSerfAddress() {
        return getIpAddress() + ":" + config.getPorts().getSerf();
    }

    /**
     * Stops the agent and cleans up any resources used by it.
     */
    @Override
    public void close() throws IOException {
        try {
            if (process != null)
                process.close();
        } finally {
            try {
                if (managedDataDir != null)
                    FileUtils.deleteDirectory(managedDataDir.toFile());
            } finally {
                if (configFile != null)
                    Files.delete(configFile);
            }
        }
    }

    private String getIpAddress() {
        return "127.0.0.1";
    }

    /**
     * Polls until the agent is ready.
     * <p>
     * The agent must be responding to requests at a minimum.
     * Additionally, if the agent is a server and bootstrap expect is 1, waits until the server has a leader.
     * Additionally, if the agent is a client, waits until the client is ready.
     *
     * @throws IOException          if there is an HTTP or lower-level problem
     * @throws NomadException       if the response signals an error or cannot be deserialized
     * @throws InterruptedException if the thread is interrupted while sleeping before retrying
     */
    public NomadResponse<?> pollUntilReady(
            NomadApiClient apiClient,
            WaitStrategy waitStrategy) throws IOException, NomadException, InterruptedException {

        if (getConfig().getServer().getEnabled() || !getConfig().getServer().getStartJoin().isEmpty()) {
            boolean shouldHaveLeader = config.getServer().getEnabled() && config.getServer().getBootstrapExpect() == 1;
            String clientName = getConfig().getClient().getEnabled() ? getConfig().getName() : null;
            return apiClient.pollUntilServerIsReady(
                    shouldHaveLeader,
                    clientName,
                    whileProcessIsRunning(process.getProcess(), waitStrategy));
        } else {
            return apiClient.pollUntilAgentIsReady(waitStrategy);
        }
    }
}
