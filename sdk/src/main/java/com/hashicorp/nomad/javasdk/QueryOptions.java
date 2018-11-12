package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;
import java.math.BigInteger;

/**
 * Options that control how an operation that queries a Nomad server is performed.
 *
 * @param <T> the response value type of the request these options will be used with
 */
public class QueryOptions<T> implements RequestOptions {
    @Nullable private String region;
    @Nullable private String namespace;
    @Nullable private BigInteger index;
    @Nullable private WaitStrategy waitStrategy;
    private boolean allowStale;
    @Nullable private Predicate<ServerQueryResponse<T>> repeatedPollPredicate;
    @Nullable private String authToken;

    /**
     * Gets the region to which requests should be forwarded.
     *
     * @return the region the request should be routed to,
     * or null if the API client's default region configuration should be used.
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#cross-region-requests">Cross-Region Requests</a>
     */
    @Nullable
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region to which the request should be forwarded.
     *
     * @param region the region the request should be routed to,
     *               or null if the API client's default region configuration should be used.
     * @return this QueryOptions instance.
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#cross-region-requests">Cross-Region Requests</a>
     */
    public QueryOptions<T> setRegion(@Nullable String region) {
        this.region = region;
        return this;
    }

    /**
     * Gets the namespace for this request.
     * <p>
     * When null, falls back to the NomadApiClient's namespace.
     */
    @Nullable
    public String getNamespace() {
        return namespace;
    }

    /**
     * Sets the namespace for this request.
     * <p>
     * When null, falls back to the NomadApiClient's namespace.
     *
     * @param namespace the namespace to use
     */
    public QueryOptions<T> setNamespace(@Nullable String namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * Gets the long-polling query index to use.
     *
     * @return the index to use
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#blocking-queries">Blocking Queries</a>
     */
    public BigInteger getIndex() {
        return index;
    }

    /**
     * Sets the long-polling query index.
     * <p>
     * The index is usually from a previous previous response's {@link ServerResponse#getIndex()} method.
     * This will cause the request to block until there is a potential change in the response.
     *
     * @param index the index to use
     * @return this QueryOptions instance.
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#blocking-queries">Blocking Queries</a>
     */
    public QueryOptions<T> setIndex(BigInteger index) {
        this.index = index;
        return this;
    }

    /**
     * Gets the wait strategy to use.
     *
     * @return the wait strategy to use, or null if a simple non-blocking request should be performed.
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#blocking-queries">Blocking Queries</a>
     */
    @Nullable
    public WaitStrategy getWaitStrategy() {
        return waitStrategy;
    }

    /**
     * Sets the wait strategy to use.
     * <p>
     * The wait strategy is used to determine the maximum amount of time to wait for a long-poll to complete.
     *
     * @param waitStrategy the wait strategy to use, or null if a simple non-blocking request should be performed.
     * @return this QueryOptions instance.
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#blocking-queries">Blocking Queries</a>
     */
    public QueryOptions<T> setWaitStrategy(WaitStrategy waitStrategy) {
        this.waitStrategy = waitStrategy;
        return this;
    }

    /**
     * Gets whether to allow stale responses.
     *
     * @return true if state responses are acceptable, false if they are not.
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#consistency-modes">Consistency Modes</a>
     */
    public boolean isAllowStale() {
        return allowStale;
    }

    /**
     * Sets whether to allow stale responses.
     * <p>
     * When true, non-leader nodes will answer from their own state, which might be slightly behind the leader.
     * You can check the response's {@link ServerQueryResponse#getMillisSinceLastContact()} to gauge the staleness of
     * the result.
     *
     * @param allowStale true if state responses are acceptable, false if they are not.
     * @return this QueryOptions instance.
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#consistency-modes">Consistency Modes</a>
     */
    public QueryOptions<T> setAllowStale(boolean allowStale) {
        this.allowStale = allowStale;
        return this;
    }

    /**
     * Gets a response predicate that we will try to satisfy
     * by repeatedly polling the server until the predicate is true or the WaitStrategy times out.
     *
     * @return the predicate to statisfy, or null if we should accept whatever is first returned
     */
    @Nullable
    public Predicate<ServerQueryResponse<T>> getRepeatedPollPredicate() {
        return repeatedPollPredicate;
    }

    /**
     * Sets a response predicate that we will try to satisfy
     * by repeatedly polling the server until the predicate is true or the WaitStrategy times out.
     *
     * @param repeatedPollPredicate the predicate to statisfy, or null if we should accept whatever is first returned
     * @return this QueryOptions instance.
     */
    public QueryOptions<T> setRepeatedPollPredicate(@Nullable Predicate<ServerQueryResponse<T>> repeatedPollPredicate) {
        this.repeatedPollPredicate = repeatedPollPredicate;
        return this;
    }

    /**
     * Returns query options that cause us to poll hoping for a response that is newer than a previous reponse.
     *
     * @param previousResponse a response whose index should be used in the query options
     * @param <T>              the response value type of the request these options will be used with
     * @return QueryOptions with the index set to the index of the previous response
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#blocking-queries">Blocking Queries</a>
     */
    public static <T> QueryOptions<T> newerThan(ServerQueryResponse<T> previousResponse) {
        return new QueryOptions<T>().setIndex(previousResponse == null ? null : previousResponse.getIndex());
    }

    /**
     * Returns query options that will poll repeatedly until a condition is met.
     *
     * @param predicate    the condition to statisfy
     * @param waitStrategy the wait strategy to use
     * @param <T>          the response value type of the request these options will be used with
     * @return QueryOptions with the given predicate and wait strategy set
     */
    public static <T> QueryOptions<T> pollRepeatedlyUntil(Predicate<ServerQueryResponse<T>> predicate,
                                                          WaitStrategy waitStrategy) {
        return new QueryOptions<T>()
                .setRepeatedPollPredicate(predicate)
                .setWaitStrategy(waitStrategy);
    }

    /**
     * Gets the secret ID of the ACL token to use for this request.
     * <p>
     * When null, falls back to the NomadApiClient's auth token.
     */
    @Nullable
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Sets the secret ID of the ACL token to use for this request.
     * <p>
     * When null, falls back to the NomadApiClient's auth token.
     *
     * @param authToken the secret ID
     */
    public QueryOptions<T> setAuthToken(@Nullable String authToken) {
        this.authToken = authToken;
        return this;
    }

}
