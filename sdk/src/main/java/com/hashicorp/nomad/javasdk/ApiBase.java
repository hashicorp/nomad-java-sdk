package com.hashicorp.nomad.javasdk;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import javax.annotation.Nullable;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Common implementation functions for the API classes that are accessible from
 * the {@link NomadApiClient}.
 */
abstract class ApiBase {
    protected final NomadApiClient apiClient;

    protected ApiBase(final NomadApiClient apiClient) {
        this.apiClient = apiClient;
    }


    // URI Building

    protected URIBuilder uri(final String path) {
        return uri(apiClient.getAddress(), path);
    }

    protected URIBuilder uri(final HttpHost address, final String path) {
        return new URIBuilder()
                .setScheme(address.getSchemeName())
                .setHost(address.getHostName())
                .setPort(address.getPort())
                .setPath(path);
    }


    // Request Building

    protected RequestBuilder get(final String path) {
        return get(uri(path));
    }

    protected RequestBuilder get(final URIBuilder uri) {
        return RequestBuilder.get(build(uri));
    }

    protected RequestBuilder put(final String path, @Nullable final WriteOptions options) {
        return put(uri(path), options);
    }

    protected RequestBuilder put(final URIBuilder uri, @Nullable final WriteOptions options) {
        return prepareWrite(RequestBuilder.put(), uri, options);
    }

    protected RequestBuilder put(final String path, final Object requestEntity, @Nullable final WriteOptions options) {
        return put(uri(path), requestEntity, options);
    }

    protected RequestBuilder put(
            final URIBuilder uri,
            final Object requestEntity,
            @Nullable final WriteOptions options
    ) {
        return prepareWrite(RequestBuilder.put(), uri, options)
                .setEntity(new StringEntity(NomadJson.serialize(requestEntity), ContentType.APPLICATION_JSON));
    }

    protected RequestBuilder delete(final URIBuilder uri, @Nullable final WriteOptions options) {
        return prepareWrite(RequestBuilder.delete(), uri, options);
    }


    // Execution

    protected <T> NomadResponse<T> executePlain(
            final RequestBuilder request,
            @Nullable final ValueExtractor<T> valueExtractor) throws IOException, NomadException {

        return apiClient.execute(request, new NomadResponseAdapter<>(valueExtractor));
    }

    protected <T> ServerResponse<T> executeServerAction(
            final RequestBuilder request,
            @Nullable final ValueExtractor<T> valueExtractor) throws IOException, NomadException {

        return apiClient.execute(request, new ServerResponseAdapter<>(valueExtractor));
    }

    protected <T> ServerQueryResponse<T> executeServerQuery(
            final String path,
            @Nullable final QueryOptions<T> options,
            @Nullable final ValueExtractor<T> valueExtractor
    ) throws IOException, NomadException {
        return executeServerQuery(uri(path), options, valueExtractor);
    }

    protected <T> ServerQueryResponse<T> executeServerQuery(
            final URIBuilder uriBuilder,
            @Nullable final QueryOptions<T> options,
            @Nullable final ValueExtractor<T> valueExtractor
    ) throws IOException, NomadException {

        final URI uri = build(uriBuilder);
        final WaitStrategy waitStrategy = options == null ? null : options.getWaitStrategy();
        final Predicate<ServerQueryResponse<T>> predicate = options == null ? null : options.getRepeatedPollPredicate();

        ServerQueryResponse<T> response = null;
        while (true) {
            response = executeServerQueryRaw(
                    options, getWait(waitStrategy, response), valueExtractor, RequestBuilder.get(uri));

            if (predicate == null || predicate.apply(response))
                return response;

            final BigInteger targetIndex = BigInteger.ZERO.equals(response.getIndex())
                    ? BigInteger.ONE
                    : response.getIndex();
            if (options.getIndex() == null || targetIndex.compareTo(options.getIndex()) < 0)
                options.setIndex(targetIndex);
        }
    }

    protected <T> ServerQueryResponse<T> executeServerQueryPut(
            final URIBuilder uriBuilder,
            Object entity,
            @Nullable final QueryOptions<T> options,
            @Nullable final ValueExtractor<T> valueExtractor
    ) throws IOException, NomadException {

        final WaitStrategy waitStrategy = options == null ? null : options.getWaitStrategy();
        final Predicate<ServerQueryResponse<T>> predicate = options == null ? null : options.getRepeatedPollPredicate();

        ServerQueryResponse<T> response = null;
        while (true) {
            response = executeServerQueryRaw(options, getWait(waitStrategy, response), valueExtractor,
                    put(uriBuilder, entity, null));

            if (predicate == null || predicate.apply(response))
                return response;

            options.setIndex(response.getIndex());
        }
    }

    protected <T extends List<?>> ServerQueryResponse<T> executeServerQueryForPrefixFilteredList(
            final String path,
            @Nullable final String prefix,
            @Nullable final QueryOptions<T> options,
            @Nullable final ValueExtractor<T> valueExtractor
    ) throws IOException, NomadException {
        final URIBuilder uri = uri(path);
        if (prefix != null) {
            uri.addParameter("prefix", prefix);
        }
        return executeServerQuery(uri, options, valueExtractor);
    }

    private URI build(URIBuilder uriBuilder) {
        try {
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> String getWait(
            @Nullable final WaitStrategy waitStrategy,
            @Nullable final ServerQueryResponse<T> lastResponse
    ) throws WaitStrategyExhaustedException {
        try {
            return waitStrategy == null ? null : waitStrategy.getWait();
        } catch (WaitStrategyExhaustedException e) {
            if (lastResponse == null) {
                // We'll try once anyway
                return "0ms";
            } else {
                throw new WaitStrategyExhaustedException(e.getMessage() + ", last response: " + lastResponse, e);
            }
        }
    }

    private <T> ServerQueryResponse<T> executeServerQueryRaw(
            @Nullable final QueryOptions<T> options,
            @Nullable final String wait,
            final ValueExtractor<T> valueExtractor,
            RequestBuilder requestBuilder
    ) throws IOException, NomadException {
        String region = apiClient.getConfig().getRegion();
        String namespace = apiClient.getConfig().getNamespace();
        String secretId = apiClient.getConfig().getSecretId();

        if (options != null) {
            if (options.getRegion() != null)
                region = options.getRegion();
            if (options.getNamespace() != null)
                namespace = options.getNamespace();
            if (options.getSecretId() != null)
                secretId = options.getSecretId();
            if (options.getIndex() != null)
                requestBuilder.addParameter("index", options.getIndex().toString());
            if (wait != null)
                requestBuilder.addParameter("wait", wait);
            if (options.isAllowStale())
                requestBuilder.addParameter("stale", null);
        }

        if (region != null)
            requestBuilder.addParameter("region", region);
        if (namespace != null)
            requestBuilder.addParameter("namespace", namespace);
        if (secretId != null)
            requestBuilder.addHeader("X-Nomad-Token", secretId);

        return apiClient.execute(requestBuilder, new ServerQueryResponseAdapter<>(valueExtractor));
    }

    private RequestBuilder prepareWrite(
            final RequestBuilder builder,
            final URIBuilder uri,
            @Nullable final WriteOptions options
    ) {
        String region = apiClient.getConfig().getRegion();
        String namespace = apiClient.getConfig().getNamespace();
        String secretId = apiClient.getConfig().getSecretId();

        if (options != null) {
            if (options.getRegion() != null)
                region = options.getRegion();
            if (options.getNamespace() != null)
                namespace = options.getNamespace();
            if (options.getSecretId() != null)
                secretId = options.getSecretId();
        }

        if (region != null)
            uri.addParameter("region", region);
        if (namespace != null)
            uri.addParameter("namespace", namespace);
        if (secretId != null)
            builder.addHeader("X-Nomad-Token", secretId);

        return builder.setUri(build(uri));
    }

    /**
     * Builds a @{link NomadResponse} from an HTTP response.
     *
     * @param <T> type that is extracted from the response body
     */
    private static class NomadResponseAdapter<T> extends ResponseAdapter<T, NomadResponse<T>> {
        NomadResponseAdapter(final ValueExtractor<T> valueExtractor) {
            super(valueExtractor);
        }

        @Override
        protected NomadResponse<T> buildResponse(
                final HttpResponse httpResponse,
                final String rawEntity,
                @Nullable final T value
        ) {
            return new NomadResponse<>(rawEntity, value);
        }
    }

    /**
     * Builds a @{link ServerResponse} from an HTTP response.
     *
     * @param <T> type that is extracted from the response body
     */
    private static class ServerResponseAdapter<T> extends ResponseAdapter<T, ServerResponse<T>> {
        ServerResponseAdapter(@Nullable final ValueExtractor<T> valueExtractor) {
            super(valueExtractor);
        }

        @Override
        protected ServerResponse<T> buildResponse(
                final HttpResponse httpResponse,
                final String rawEntity,
                @Nullable final T value
        ) {
            return new ServerResponse<>(httpResponse, rawEntity, value);
        }
    }

    /**
     * Builds a @{link ServerQueryResponse} from an HTTP response.
     *
     * @param <T> type that is extracted from the response body
     */
    private static class ServerQueryResponseAdapter<T> extends ResponseAdapter<T, ServerQueryResponse<T>> {
        ServerQueryResponseAdapter(@Nullable final ValueExtractor<T> valueExtractor) {
            super(valueExtractor);
        }

        @Override
        protected ServerQueryResponse<T> buildResponse(
                final HttpResponse httpResponse,
                final String rawEntity,
                @Nullable final T value
        ) {
            return new ServerQueryResponse<>(httpResponse, rawEntity, value);
        }
    }

}
