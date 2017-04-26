package com.hashicorp.nomad.javasdk;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import javax.annotation.Nullable;
import java.io.IOException;
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

    protected RequestBuilder delete(final String path, @Nullable final WriteOptions options) {
        return prepareWrite(RequestBuilder.delete(), uri(path), options);
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
            response = executeServerQueryRaw(uri, options, getWait(waitStrategy, response), valueExtractor);

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
            final URI uri,
            @Nullable final QueryOptions<T> options,
            @Nullable final String wait,
            final ValueExtractor<T> valueExtractor
    ) throws IOException, NomadException {
        final RequestBuilder request = RequestBuilder.get(uri);
        String region = null;
        if (options != null) {
            region = options.getRegion();
            if (options.getIndex() != null)
                request.addParameter("index", options.getIndex().toString());
            if (wait != null)
                request.addParameter("wait", wait);
            if (options.isAllowStale())
                request.addParameter("stale", null);
        }
        if (region == null)
            region = apiClient.getConfig().getRegion();
        if (region != null)
            request.addParameter("region", region);

        return apiClient.execute(request, new ServerQueryResponseAdapter<>(valueExtractor));
    }

    private RequestBuilder prepareWrite(
            final RequestBuilder builder,
            final URIBuilder uri,
            @Nullable final WriteOptions options
    ) {
        String region = null;
        if (options != null)
            region = options.getRegion();
        if (region == null)
            region = apiClient.getConfig().getRegion();
        if (region != null)
            uri.addParameter("region", region);

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
