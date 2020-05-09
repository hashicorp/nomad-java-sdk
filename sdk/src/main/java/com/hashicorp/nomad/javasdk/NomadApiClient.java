package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.Node;
import com.hashicorp.nomad.apimodel.NodeListStub;
import com.hashicorp.nomad.apimodel.OperatorHealthReply;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;

import javax.annotation.Nullable;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.List;

import static com.hashicorp.nomad.javasdk.NomadPredicates.isHealthy;
import static com.hashicorp.nomad.javasdk.NomadPredicates.hadKnownLeader;
import static com.hashicorp.nomad.javasdk.NomadPredicates.clientNodeIsReady;
import static com.hashicorp.nomad.javasdk.NomadPredicates.responseValue;
import static com.hashicorp.nomad.javasdk.NomadPredicates.both;

/**
 * An asynchronous client for the
 * <a href="https://www.nomadproject.io/docs/http">Nomad HTTP API</a>.
 */
public final class NomadApiClient implements Closeable, AutoCloseable {
    private NomadApiConfiguration config;
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
     * @param config     configuration for the API
     * @param httpClient the HTTP to client to use.
     *                   If null, a client will be built using the provided API configuration.
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
     * Sets the active namespace of this API client.
     *
     * @param namespace the namespace to use
     */
    public void setNamespace(String namespace) {
        config = config.withNamespace(namespace);
    }

    /**
     * Sets the active ACL token secret ID that this client passes to the server.
     *
     * @param authToken the secret ID to use
     */
    public void setAuthToken(String authToken) {
        config = config.withAuthToken(authToken);
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
     * Returns an API for managing ACL policies.
     */
    public AclPoliciesApi getAclPoliciesApi() {
        return new AclPoliciesApi(this);
    }

    /**
     * Returns an API for managing ACL tokens.
     */
    public AclTokensApi getAclTokensApi() {
        return new AclTokensApi(this);
    }

    /**
     * Returns an API for managing CSI plugins.
     */
    public CSIPluginsApi getCSIPlugins() {
        return new CSIPluginsApi(this);
    }

    /**
     * Returns an API for managing CSI volumes.
     */
    public CSIVolumesApi getCSIVolumesApi() {
        return new CSIVolumesApi(this);
    }

    /**
     * Returns an API for managing deployments.
     */
    public DeploymentsApi getDeploymentsApi() {
        return new DeploymentsApi(this);
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
     * Returns an API for managing namespaces.
     */
    public NamespacesApi getNamespacesApi() {
        return new NamespacesApi(this);
    }

    /**
     * Returns an API for querying information about the client nodes in the Nomad cluster.
     */
    public NodesApi getNodesApi() {
        return new NodesApi(this);
    }

    /**
     * Returns an API for operating the Nomad cluster.
     */
    public OperatorApi getOperatorApi() {
        return new OperatorApi(this);
    }

    /**
     * Returns an API for managing quotas.
     */
    public QuotasApi getQuotasApi() {
        return new QuotasApi(this);
    }

    /**
     * Returns an API for listing the regions in the Nomad cluster.
     */
    public RegionsApi getRegionsApi() {
        return new RegionsApi(this);
    }

    /**
     * Returns an API for accessing scaling policies.
     */
    public ScalingApi getScalingApi() {
        return new ScalingApi(this);
    }

    /**
     * Returns an API for searching for items in Nomad cluster.
     */
    public SearchApi getSearchApi() {
        return new SearchApi(this);
    }

    /**
     * Returns an API for managing ACL policies.
     */
    public SentinelPoliciesApi getSentinelPoliciesApi() {
        return new SentinelPoliciesApi(this);
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
     * @param waitStrategy the wait strategy to use while polling
     * @return a future that completes successfully when the API is ready
     * @throws IOException          if there is an HTTP or lower-level problem
     * @throws NomadException       if the response signals an error or cannot be deserialized
     * @throws InterruptedException if the thread is interrupted while waiting to poll again
     */
    public NomadResponse<?> pollUntilAgentIsReady(final WaitStrategy waitStrategy)
            throws IOException, NomadException, InterruptedException {

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

    /**
     * Polls until the autopilot indicates that the cluster is healthy.
     * <p>
     * At a minimum, the API must be responding to API requests.
     *
     * @param waitStrategy the wait strategy to use while polling
     * @return a future that completes successfully when the API is ready
     * @throws IOException          if there is an HTTP or lower-level problem
     * @throws NomadException       if the response signals an error or cannot be deserialized
     * @throws InterruptedException if the thread is interrupted while waiting to poll again
     */
    public NomadResponse<?> pollUntilClusterHealthy(final WaitStrategy waitStrategy)
            throws IOException, NomadException, InterruptedException {

        Predicate<ServerQueryResponse<OperatorHealthReply>> predicate = isHealthy();

        while (true) {
            try {
                return getOperatorApi().getHealth(
                        QueryOptions
                                .pollRepeatedlyUntil(predicate, waitStrategy)
                                .setAllowStale(true)
                );
            } catch (ConnectException e) {
                // we'll try again
            } catch (ErrorResponseException e) {
                if (e.getServerErrorCode() != 429) {
                    throw e;
                }
            }
            Thread.sleep(100);
            waitStrategy.getWait();
        }
    }

    <R extends NomadResponse<?>> R execute(
            final RequestBuilder requestBuilder,
            final ResponseAdapter<?, R> responseAdapter,
            @Nullable final RequestOptions requestOptions
    ) throws IOException, NomadException {
        final HttpUriRequest request = buildRequest(requestBuilder, requestOptions);
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

    InputStream executeRawStream(
            final RequestBuilder requestBuilder,
            @Nullable final RequestOptions requestOptions
    )
            throws IOException, NomadException {

        final HttpUriRequest request = buildRequest(requestBuilder, requestOptions);
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

    FramedStream executeFramedStream(
            final RequestBuilder requestBuilder,
            @Nullable final RequestOptions requestOptions
    )
            throws IOException, NomadException {

        final HttpUriRequest request = buildRequest(requestBuilder, requestOptions);
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

    HttpHost getAddress() {
        return config.getAddress();
    }

    private HttpUriRequest buildRequest(
            RequestBuilder requestBuilder,
            @Nullable RequestOptions options
    ) {
        String region =    getConfig().getRegion();
        String namespace = getConfig().getNamespace();
        String authToken = getConfig().getAuthToken();

        if (options != null) {
            if (options.getRegion() != null)
                region = options.getRegion();
            if (options.getNamespace() != null)
                namespace = options.getNamespace();
            if (options.getAuthToken() != null)
                authToken = options.getAuthToken();
        }

        if (region != null && !region.isEmpty())
            requestBuilder.addParameter("region", region);
        if (namespace != null && !namespace.isEmpty())
            requestBuilder.addParameter("namespace", namespace);
        if (authToken != null && !authToken.isEmpty())
            requestBuilder.addHeader("X-Nomad-Token", authToken);

        return requestBuilder.build();
    }

    private CloseableHttpClient buildHttpClient(NomadApiConfiguration config) {

        return HttpClientBuilder.create()
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
                    @Override
                    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                        final long serverKeepAlive = super.getKeepAliveDuration(response, context);
                        return serverKeepAlive > 0 ? serverKeepAlive : 60000;
                    }
                })
                .setRetryHandler(new DefaultHttpRequestRetryHandler() {
                    @Override
                    protected boolean handleAsIdempotent(HttpRequest request) {
                        return true;
                    }
                })
                .setSSLContext(buildSslContext(config.getTls()))
                .setSSLHostnameVerifier(new NomadHostnameVerifier())
                .build();
    }

    private static SSLContext buildSslContext(NomadApiConfiguration.Tls tls) {
        try {
            SSLContext context = SSLContext.getInstance("TLS");

            KeyManager[] keyManagers = tls.getClientKeyFile() == null
                    ? null
                    : TlsUtils.usePemCertificateAndKey(tls.getClientCertificateFile(), tls.getClientKeyFile());

            TrustManager[] trustManagers = tls.isSkipVerify()
                    ? TlsUtils.trustAnyCertificate()
                    : tls.getCaCertificateFile() != null
                        ? TlsUtils.trustPemCertificate(tls.getCaCertificateFile())
                        : null;

            context.init(keyManagers, trustManagers, null);
            return context;
        } catch (Throwable e) {
            throw new RuntimeException("There was an error building the SSLContext: " + e, e);
        }
    }
}
