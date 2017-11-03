package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.QuotaSpec;
import com.hashicorp.nomad.apimodel.QuotaUsage;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * API for managing quotas,
 * exposing the <a href="https://www.nomadproject.io/api/quotas.html">ACL policies</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class QuotasApi extends ApiBase {

    static final Comparator<QuotaSpec> NEWEST_TO_OLDEST_SPECS = new Comparator<QuotaSpec>() {
        @Override
        public int compare(QuotaSpec o1, QuotaSpec o2) {
            return o2.getCreateIndex().compareTo(o1.getCreateIndex());
        }
    };

    static final Comparator<QuotaUsage> NEWEST_TO_OLDEST_USAGES = new Comparator<QuotaUsage>() {
        @Override
        public int compare(QuotaUsage o1, QuotaUsage o2) {
            return o2.getCreateIndex().compareTo(o1.getCreateIndex());
        }
    };

    QuotasApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Deletes a quota specification.
     *
     * @param quotaName the name of the quota to delete
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#delete-quota-specification">{@code DELETE /v1/quota/:name}</a>
     */
    public ServerResponse<Void> delete(final String quotaName) throws IOException, NomadException {
        return delete(quotaName, null);
    }

    /**
     * Deletes a quota specification.
     *
     * @param quotaName the name of the quota to delete
     * @param options   options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#delete-quota-specification">{@code DELETE /v1/quota/:name}</a>
     */
    public ServerResponse<Void> delete(final String quotaName, @Nullable final WriteOptions options)
            throws IOException, NomadException {

        return executeServerAction(delete(uri("/v1/quota/" + quotaName), options), null);
    }

    /**
     * Queries a quota specification.
     *
     * @param name name of the quota.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#read-quota-specification">{@code GET /v1/quota/:name}</a>
     */
    public ServerQueryResponse<QuotaSpec> info(final String name) throws IOException, NomadException {
        return info(name, null);
    }

    /**
     * Queries a quota specification.
     *
     * @param name    name of the quota.
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#read-quota-specification">{@code GET /v1/quota/:name}</a>
     */
    public ServerQueryResponse<QuotaSpec> info(
            final String name,
            @Nullable final QueryOptions<QuotaSpec> options
    ) throws IOException, NomadException {
        return executeServerQuery("/v1/quota/" + name, options, NomadJson.parserFor(QuotaSpec.class));
    }

    /**
     * Lists all quota specifications.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#list-quota-specifications">{@code GET /v1/quotas}</a>
     */
    public ServerQueryResponse<List<QuotaSpec>> list() throws IOException, NomadException {
        return list(null, null);
    }

    /**
     * Lists all quota specifications.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#list-quota-specifications">{@code GET /v1/quotas}</a>
     */
    public ServerQueryResponse<List<QuotaSpec>> list(
            @Nullable final QueryOptions<List<QuotaSpec>> options
    ) throws IOException, NomadException {

        return list(null, options);
    }

    /**
     * Lists quota specifications.
     *
     * @param namePrefix a name prefix that, if given,
     *                   restricts the results to only quotas having a name with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#list-quota-specifications">{@code GET /v1/quotas}</a>
     */
    public ServerQueryResponse<List<QuotaSpec>> list(@Nullable final String namePrefix)
            throws IOException, NomadException {

        return list(namePrefix, null);
    }

    /**
     * Lists quota specifications.
     *
     * @param namePrefix a name prefix that, if given,
     *                   restricts the results to only quotas having a name with this prefix
     * @param options    options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#list-quota-specifications">{@code GET /v1/quotas}</a>
     */
    public ServerQueryResponse<List<QuotaSpec>> list(
            @Nullable final String namePrefix,
            @Nullable final QueryOptions<List<QuotaSpec>> options
    ) throws IOException, NomadException {

        return executeServerQueryForPrefixFilteredList(
                "/v1/quotas",
                namePrefix,
                options,
                NomadJson.parserForSortedListOf(QuotaSpec.class, NEWEST_TO_OLDEST_SPECS));
    }

    /**
     * Lists all quota usages.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#list-quota-usages">{@code GET /v1/quota-usages}</a>
     */
    public ServerQueryResponse<List<QuotaUsage>> listUsage() throws IOException, NomadException {
        return listUsage(null, null);
    }

    /**
     * Lists all quota usages.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#list-quota-usages">{@code GET /v1/quota-usages}</a>
     */
    public ServerQueryResponse<List<QuotaUsage>> listUsage(
            @Nullable final QueryOptions<List<QuotaUsage>> options
    ) throws IOException, NomadException {

        return listUsage(null, options);
    }

    /**
     * Lists quota usages.
     *
     * @param namePrefix a name prefix that, if given,
     *                   restricts the results to only quotas having a name with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#list-quota-usages">{@code GET /v1/quota-usages}</a>
     */
    public ServerQueryResponse<List<QuotaUsage>> listUsage(@Nullable final String namePrefix)
            throws IOException, NomadException {

        return listUsage(namePrefix, null);
    }

    /**
     * Lists quota usages.
     *
     * @param namePrefix a name prefix that, if given,
     *                   restricts the results to only quotas having a name with this prefix
     * @param options    options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#list-quota-usages">{@code GET /v1/quota-usages}</a>
     */
    public ServerQueryResponse<List<QuotaUsage>> listUsage(
            @Nullable final String namePrefix,
            @Nullable final QueryOptions<List<QuotaUsage>> options
    ) throws IOException, NomadException {

        return executeServerQueryForPrefixFilteredList(
                "/v1/quota-usages",
                namePrefix,
                options,
                NomadJson.parserForSortedListOf(QuotaUsage.class, NEWEST_TO_OLDEST_USAGES));
    }

    /**
     * Registers or updates a quota.
     *
     * @param quota the quota to register
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#create-or-update-quota-specification">{@code PUT /v1/quota}</a>
     */
    public ServerResponse<Void> register(QuotaSpec quota) throws IOException, NomadException {
        return register(quota, null);
    }

    /**
     * Registers or updates a quota.
     *
     * @param quota   the quota to register
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#create-or-update-quota-specification">{@code PUT /v1/quota}</a>
     */
    public ServerResponse<Void> register(QuotaSpec quota, @Nullable WriteOptions options)
            throws IOException, NomadException {
        return executeServerAction(put("/v1/quota", quota, options), null);
    }

    /**
     * Queries a quota usage.
     *
     * @param name name of the quota.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#read-quota-usage">{@code GET /v1/quota/:name}</a>
     */
    public ServerQueryResponse<QuotaUsage> usage(final String name) throws IOException, NomadException {
        return usage(name, null);
    }

    /**
     * Queries a quota usage.
     *
     * @param name    name of the quota.
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/quotas.html#read-quota-usage">{@code GET /v1/quota/:name}</a>
     */
    public ServerQueryResponse<QuotaUsage> usage(
            final String name,
            @Nullable final QueryOptions<QuotaUsage> options
    ) throws IOException, NomadException {
        return executeServerQuery("/v1/quota/usage/" + name, options, NomadJson.parserFor(QuotaUsage.class));
    }
}
