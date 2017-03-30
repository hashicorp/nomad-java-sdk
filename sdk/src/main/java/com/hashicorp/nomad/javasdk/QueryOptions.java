package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;
import java.math.BigInteger;

/**
 * Options for queries to a Nomad server API.
 *
 * @param <T> the expected response type.
 */
public class QueryOptions<T> {
    @Nullable private String region;
    @Nullable private BigInteger index;
    @Nullable private WaitStrategy waitStrategy;
    private boolean allowStale;
    @Nullable private Predicate<ServerQueryResponse<T>> repeatedPollPredicate;

    /**
     * Gets the region to which the request should be forwarded.
     * <p>
     * When null, falls back to the NomadApiClient's region.
     *
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#cross-region-requests">Cross-Region Requests</a>
     */
    @Nullable
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region to which the request should be forwarded.
     * <p>
     * When null, falls back to the NomadApiClient's region.
     *
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#cross-region-requests">Cross-Region Requests</a>
     */
    public QueryOptions<T> setRegion(@Nullable String region) {
        this.region = region;
        return this;
    }

    /**
     * Gets the long-polling query index to use.
     *
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
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#blocking-queries">Blocking Queries</a>
     */
    public QueryOptions<T> setIndex(BigInteger index) {
        this.index = index;
        return this;
    }

    /**
     * Gets the wait strategy to use.
     *
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#blocking-queries">Blocking Queries</a>
     */
    public WaitStrategy getWaitStrategy() {
        return waitStrategy;
    }

    /**
     * Sets the wait strategy to use.
     * <p>
     * The wait strategy is used to determine the maximum amount of time to wait for a long-poll to complete.
     *
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#blocking-queries">Blocking Queries</a>
     */
    public QueryOptions<T> setWaitStrategy(WaitStrategy waitStrategy) {
        this.waitStrategy = waitStrategy;
        return this;
    }

    /**
     * Gets whether to allow stale responses.
     *
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
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#consistency-modes">Consistency Modes</a>
     */
    public QueryOptions<T> setAllowStale(boolean allowStale) {
        this.allowStale = allowStale;
        return this;
    }

    /**
     * Gets the predicate to use for repeated polling.
     * <p>
     * When null, there will be no repeated polling. When non-null, the server will be repeatedly polled until either
     * the predicate is true, or the wait strategy throws a TimeoutException.
     */
    @Nullable
    public Predicate<ServerQueryResponse<T>> getRepeatedPollPredicate() {
        return repeatedPollPredicate;
    }

    /**
     * Set the predicate to use for repeated polling.
     * <p>
     * When null, there will be no repeated polling. When non-null, the server will be repeatedly polled until either
     * the predicate is true, or the wait strategy throws a TimeoutException.
     */
    public QueryOptions<T> setRepeatedPollPredicate(@Nullable Predicate<ServerQueryResponse<T>> repeatedPollPredicate) {
        this.repeatedPollPredicate = repeatedPollPredicate;
        return this;
    }

    /**
     * Returns query options with the long-poll index to the index of the given response.
     *
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#blocking-queries">Blocking Queries</a>
     */
    public static <T> QueryOptions<T> newerThan(ServerQueryResponse<T> previousResponse) {
        return new QueryOptions<T>().setIndex(previousResponse == null ? null : previousResponse.getIndex());
    }

    /**
     * Returns query options with the given repeated poll predicate and wait strategy.
     */
    public static <T> QueryOptions<T> pollRepeatedlyUntil(Predicate<ServerQueryResponse<T>> predicate,
                                                          WaitStrategy waitStrategy) {
        return new QueryOptions<T>()
                .setRepeatedPollPredicate(predicate)
                .setWaitStrategy(waitStrategy);
    }
}
