package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.Node;
import com.hashicorp.nomad.apimodel.NodeListStub;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HttpContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.cache.CachingHttpClientBuilder;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.ExponentialBackOffSchedulingStrategy;

import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.List;

import static com.hashicorp.nomad.javasdk.NomadPredicates.both;
import static com.hashicorp.nomad.javasdk.NomadPredicates.clientNodeIsReady;
import static com.hashicorp.nomad.javasdk.NomadPredicates.hadKnownLeader;
import static com.hashicorp.nomad.javasdk.NomadPredicates.responseValue;

/**
 * An asynchronous client for the
 * <a href="https://www.nomadproject.io/docs/http">Nomad HTTP API</a>.
 */
public final class NomadApiClient implements Closeable, AutoCloseable {
    private final NomadApiConfiguration config;
    private final CloseableHttpClient httpClient;

    /**
     * Creates an API client.
     *
     * @param address the scheme (http or https), host and port of the agent to connect to,
     *                e.g. "http://localhost:4646"
     */
    public NomadApiClient(final String address) {
        this(new NomadApiConfiguration.Builder().setAddress(address).build());
    }


    /**
     * Creates an API client.
     *
     * @param address the scheme (http or https), host and port of the agent to connect to
     */
    public NomadApiClient(final HttpHost address) {
        this(new NomadApiConfiguration.Builder().setAddress(address).build());
    }

    /**
     * Creates a new API client.
     *
     * @param config the configuration for the new API client
     */
    public NomadApiClient(NomadApiConfiguration config) {
        this(config, null);
    }

    /**
     * Creates an API client using the given configuration.
     *
     * @param httpClient the HTTP to client to use.
     *                   If null, a client will be built using the provided configuration.
     */
    public NomadApiClient(NomadApiConfiguration config, @Nullable CloseableHttpClient httpClient) {
        this.config = config;
        this.httpClient = httpClient != null ? httpClient : buildHttpClient(config);
    }

    /**
     * Returns the API client's configuration.
     */
    public NomadApiConfiguration getConfig() {
        return config;
    }

    /**
     * Closes the underlying HTTP client.
     */
    @Override
    public void close() throws IOException {
        httpClient.close();
    }

    /**
     * Returns an API for agent a cluster management.
     */
    public AgentApi getAgentApi() {
        return new AgentApi(this);
    }

    /**
     * Returns an API for querying information about allocations.
     */
    public AllocationsApi getAllocationsApi() {
        return new AllocationsApi(this);
    }

    /**
     * Returns an API for interacting directly a client node.
     *
     * @param node the client node to connect to
     */
    public ClientApi getClientApi(final Node node) {
        return getClientApi(HttpHost.create(
                node.getTlsEnabled()
                        ? "https://" + node.getHttpAddr()
                        : node.getHttpAddr()));
    }

    /**
     * Returns an API for interacting directly with a client node.
     *
     * @param nodeAddress the HTTP or HTTPS url for the client node in the format "scheme://host:port"
     */
    public ClientApi getClientApi(final HttpHost nodeAddress) {
        return new ClientApi(this, nodeAddress);
    }

    /**
     * Returns an API for interacting directly with a client node after looking up its address.
     *
     * @param nodeId the nodeId of the client node to connect to
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     */
    public ClientApi lookupClientApiByNodeId(String nodeId) throws IOException, NomadException {
        return getClientApi(getNodesApi().info(nodeId).getValue());
    }

    /**
     * Returns an API for querying information about evaluations.
     */
    public EvaluationsApi getEvaluationsApi() {
        return new EvaluationsApi(this);
    }

    /**
     * Returns an API for submitting and managing jobs.
     */
    public JobsApi getJobsApi() {
        return new JobsApi(this);
    }

    /**
     * Returns an API for querying information about the client nodes in the Nomad cluster.
     */
    public NodesApi getNodesApi() {
        return new NodesApi(this);
    }

    /**
     * Returns an API for listing the regions in the Nomad cluster.
     */
    public RegionsApi getRegionsApi() {
        return new RegionsApi(this);
    }

    /**
     * Returns an API for querying the status of the Nomad cluster.
     */
    public StatusApi getStatusApi() {
        return new StatusApi(this);
    }

    /**
     * Returns an API for performing system maintenance operations on the Nomad cluster.
     */
    public SystemApi getSystemApi() {
        return new SystemApi(this);
    }

    /**
     * Polls until the server API on the remote agent is ready.
     * <p>
     * At a minimum, the API must be responding to server API requests.
     *
     * @param shouldHaveLeader if true, polling continues until there is a known leader.
     * @param clientName       if not null, polling until the client node with the given name is ready.
     * @param waitStrategy     the wait strategy to use for polling
     * @return a future that completes successfully when the API is ready
     * @throws IOException          if there is an HTTP or lower-level problem
     * @throws NomadException       if the response signals an error or cannot be deserialized
     * @throws InterruptedException if the thread is interrupted while waiting to poll again
     */
    public ServerQueryResponse<?> pollUntilServerIsReady(
            boolean shouldHaveLeader,
            @Nullable final String clientName,
            final WaitStrategy waitStrategy) throws IOException, NomadException, InterruptedException {

        Predicate<ServerQueryResponse<List<NodeListStub>>> predicate = null;

        if (shouldHaveLeader)
            predicate = hadKnownLeader();

        if (clientName != null) {
            Predicate<ServerQueryResponse<List<NodeListStub>>> clientIsReady =
                    responseValue(clientNodeIsReady(clientName));
            predicate = predicate == null ? clientIsReady : both(predicate, clientIsReady);
        }

        while (true) {
            try {
                return getNodesApi().list(
                        QueryOptions
                                .pollRepeatedlyUntil(predicate, waitStrategy)
                                .setAllowStale(true)
                );
            } catch (ConnectException e) {
                // we'll try again
            }
            Thread.sleep(100);
            waitStrategy.getWait();
        }
    }

    /**
     * Polls until the API on the remote agent is ready.
     * <p>
     * At a minimum, the API must be responding to API requests.
     *
     * @return a future that completes successfully when the API is ready
     * @throws IOException          if there is an HTTP or lower-level problem
     * @throws NomadException       if the response signals an error or cannot be deserialized
     * @throws InterruptedException if the thread is interrupted while waiting to poll again
     */
    public NomadResponse<?> pollUntilAgentIsReady(final WaitStrategy waitStrategy)
            throws IOException, NomadException, InterruptedException {

        Predicate<ServerQueryResponse<List<NodeListStub>>> predicate = null;

        while (true) {
            try {
                return getAgentApi().self();
            } catch (ConnectException e) {
                // we'll try again
            }
            Thread.sleep(100);
            waitStrategy.getWait();
        }
    }

    <R extends NomadResponse<?>> R execute(
            final RequestBuilder requestBuilder,
            final ResponseAdapter<?, R> responseAdapter
    ) throws IOException, NomadException {
        final HttpUriRequest request = requestBuilder.build();
        try (final CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw ErrorResponseException.signaledInStatus(request, response);
            }
            try {
                return responseAdapter.apply(response);
            } catch (ErrorFoundInResponseEntityException e) {
                throw ErrorResponseException.signaledInEntity(request, response, e.getMessage());
            }
        }
    }

    HttpHost getAddress() {
        return config.getAddress();
    }

    InputStream executeRawStream(final RequestBuilder requestBuilder)
            throws IOException, NomadException {

        final HttpUriRequest request = requestBuilder.build();
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw ErrorResponseException.signaledInStatus(request, response);
            }
            return response.getEntity().getContent();
        } catch (Throwable e) {
            response.close();
            throw e;
        }
    }

    FramedStream executeFramedStream(final RequestBuilder requestBuilder)
            throws IOException, NomadException {

        final HttpUriRequest request = requestBuilder.build();
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw ErrorResponseException.signaledInStatus(request, response);
            }
            return new FramedStream(response);
        } catch (Throwable e) {
            response.close();
            throw e;
        }
    }

    private CloseableHttpClient buildHttpClient(NomadApiConfiguration config) {
        return CachingHttpClientBuilder
                .create()
                .setSchedulingStrategy(new ExponentialBackOffSchedulingStrategy(CacheConfig.DEFAULT))
                .setRetryHandler(new DefaultHttpRequestRetryHandler() {
                    @Override
                    protected boolean handleAsIdempotent(HttpRequest request) {
                        return true;
                    }
                    @Override
                    public boolean retryRequest(final IOException exception,
                                                   final int executionCount,
                                                   final HttpContext context) {
                        final HttpClientContext clientContext = HttpClientContext.adapt(context);
                        final HttpRequest request = clientContext.getRequest();
                        return handleAsIdempotent(request);
                    }
                })
                .setSSLContext(buildSslContext(config.getTls()))
                .setSSLHostnameVerifier(new NomadHostnameVerifier())
                .build();
    }

    private static SSLContext buildSslContext(NomadApiConfiguration.Tls tls) {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(
                    tls.getClientKeyFile() == null
                            ? null
                            : TlsUtils.usePemCertificateAndKey(tls.getClientCertificateFile(), tls.getClientKeyFile()),
                    tls.getCaCertificateFile() == null
                            ? null
                            : TlsUtils.trustPemCertificate(tls.getCaCertificateFile()),
                    null);
            return context;
        } catch (Throwable e) {
            throw new RuntimeException("There was an error building the SSLContext: " + e, e);
        }
    }
}
