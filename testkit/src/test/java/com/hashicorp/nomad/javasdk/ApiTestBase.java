package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.EphemeralDisk;
import com.hashicorp.nomad.apimodel.Evaluation;
import com.hashicorp.nomad.apimodel.Job;
import com.hashicorp.nomad.apimodel.LogConfig;
import com.hashicorp.nomad.apimodel.Resources;
import com.hashicorp.nomad.apimodel.Task;
import com.hashicorp.nomad.apimodel.TaskGroup;
import com.hashicorp.nomad.testutils.TestAgent;
import com.hashicorp.nomad.testutils.NomadAgentConfiguration;
import com.hashicorp.nomad.testutils.NomadAgentProcess;
import com.hashicorp.nomad.testutils.TestMethodLogRule;
import org.hamcrest.Matcher;
import org.junit.Rule;

import java.io.IOException;
import java.math.BigInteger;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;

public class ApiTestBase {

    protected static final int TEST_WAIT_SECONDS = 20;
    protected String NONSENSE_GUID = "12345678-abcd-efab-cdef-123456789abc";

    @Rule
    public TestMethodLogRule log = new TestMethodLogRule();

    /**
     * Starts a new test agent in server-only mode.
     * <p>
     * This is sufficient for many tests, and skips the overhead of waiting for the client to initialize.
     */
    protected TestAgent newServer() throws Exception {
        return newAgent(new NomadAgentConfiguration.Builder());
    }

    /**
     * Starts a new test agent with both server and client enabled.
     * <p>
     * This is useful for tests that need allocations to actually be created.
     */
    protected TestAgent newClientServer() throws Exception {
        return newAgent(new NomadAgentConfiguration.Builder().setClientEnabled(true).addClientOption("driver.raw_exec.enable", "1"));
    }

    protected TestAgent newAgent(NomadAgentConfiguration.Builder agentConfigBuilder) throws Exception {
        return newAgent(agentConfigBuilder, new NomadApiConfiguration.Builder());
    }

    protected TestAgent newAgent(NomadAgentConfiguration.Builder agentConfigBuilder, NomadApiConfiguration.Builder apiConfigBuilder) throws Exception {
        final NomadAgentConfiguration agentConfig = agentConfigBuilder.build();
        log.println("Configuration: " + agentConfig);

        return new TestAgent(new NomadAgentProcess(log, agentConfig), apiConfigBuilder);
    }

    protected static Matcher<String> nonEmptyString() {
        return not(emptyOrNullString());
    }

    /**
     * Checks metadata for server query requests made before any action is taken that would have altered their output
     */
    protected void assertPristineServerQueryResponse(ServerQueryResponse<?> response) throws ResponseHeaderException {
        assertThat("response", response, not(nullValue()));
        assertThat("should have known leader", response.hadKnownLeader(), is(true));
        assertThat("should have last contact", response.getMillisSinceLastContact(), equalTo(0L));
        assertThat("index of " + response, response.getIndex(), is(BigInteger.ZERO));
    }

    /**
     * Checks metadata for server query requests made after actions that would have altered their output
     */
    protected void assertUpdatedServerQueryResponse(ServerQueryResponse<?> response) throws ResponseHeaderException {
        assertThat("response", response, not(nullValue()));
        assertThat("should have known leader", response.hadKnownLeader(), is(true));
        assertThat("should have last contact", response.getMillisSinceLastContact(), equalTo(0L));
        assertThat("index of " + response, response.getIndex(), greaterThan(BigInteger.ZERO));
    }

    /**
     * Checks metadata for server write requests
     */
    protected void assertUpdatedServerResponse(ServerResponse<?> response) {
        assertThat("response", response, not(nullValue()));
        assertThat("index of " + response, response.getIndex(), greaterThan(BigInteger.ZERO));
    }

    protected WaitStrategy waitStrategyForTest() {
        return WaitStrategy.waitForSeconds(TEST_WAIT_SECONDS);
    }

    protected Job createTestJob() {

        Task task = new Task()
                .setName("task1")
                .setDriver("mock_driver")
                .addConfig("run_for", "20s")
                .setResources(new Resources()
                        .setCpu(100)
                        .setMemoryMb(256)
                )
                .setLogConfig(new LogConfig()
                        .setMaxFiles(1)
                        .setMaxFileSizeMb(2)
                );

        TaskGroup taskGroup = new TaskGroup()
                .setName("group1")
                .setCount(1)
                .addTasks(task)
                .setEphemeralDisk(new EphemeralDisk()
                        .setSizeMb(25)
                );

        return new Job()
                .setId("job1")
                .setName("redis")
                .setRegion("region1")
                .setType("batch")
                .setPriority(1)
                .addDatacenters("dc1")
                .addTaskGroups(taskGroup);
    }

    protected Evaluation registerTestJobAndPollUntilEvaluationCompletesSuccessfully(TestAgent agent) throws IOException, NomadException {
        return registerTestJobAndPollUntilEvaluationCompletesSuccessfully(agent, createTestJob());
    }

    protected Evaluation registerTestJobAndPollUntilEvaluationCompletesSuccessfully(TestAgent agent, Job job) throws IOException, NomadException {
        Evaluation evaluation = registerTestJobAndPollUntilEvaluationCompletes(agent, job);
        assertThat("Evaluation was blocked", evaluation.getBlockedEval(), emptyOrNullString());
        return evaluation;
    }

    protected Evaluation registerTestJobAndPollUntilEvaluationCompletes(TestAgent agent, Job job) throws IOException, NomadException {
        EvaluationResponse registerResponse = agent.getApiClient().getJobsApi().register(job);
        assertUpdatedServerResponse(registerResponse);
        String evalID = registerResponse.getValue();
        assertThat(evalID, is(not("0")));

        Evaluation evaluation = agent.getApiClient().getEvaluationsApi().pollForCompletion(evalID, waitStrategyForTest()).getValue();
        assertThat(evaluation.getId(), is(evalID));
        assertThat(evaluation.getNextEval(), emptyOrNullString());
        return evaluation;
    }

    protected Job createBashJob(String bashCommand) {
        Task task = new Task()
                .setName("task1")
                .setDriver("raw_exec")
                .addConfig("command", "bash")
                .addConfig("args", asList("-c", bashCommand))
                .setResources(new Resources()
                        .setCpu(100)
                        .setMemoryMb(256)
                )
                .setLogConfig(new LogConfig()
                        .setMaxFiles(1)
                        .setMaxFileSizeMb(2)
                );

        TaskGroup taskGroup = new TaskGroup()
                .setName("group1")
                .setCount(1)
                .addTasks(task)
                .setEphemeralDisk(new EphemeralDisk()
                        .setSizeMb(25)
                );

        return new Job()
                .setId("job1")
                .setName("redis")
                .setRegion("region1")
                .setType("batch")
                .setPriority(1)
                .addDatacenters("dc1")
                .addTaskGroups(taskGroup);
    }
}
