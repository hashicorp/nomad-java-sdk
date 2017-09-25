package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.Namespace;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * API for querying for information about namespace,
 * exposing the <a href="https://www.nomadproject.io/api/namespaces.html">namespaces</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class NamespacesApi extends ApiBase {

    static final Comparator<Namespace> NEWEST_TO_OLDEST = new Comparator<Namespace>() {
        @Override
        public int compare(Namespace o1, Namespace o2) {
            return o2.getCreateIndex().compareTo(o1.getCreateIndex());
        }
    };

    NamespacesApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Deletes a namespace.
     *
     * @param namespaceId ID of the namespace to delete
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/namespaces.html#delete-namespace">{@code DELETE /v1/namespace/:id}</a>
     */
    public ServerResponse<Void> delete(final String namespaceId) throws IOException, NomadException {
        return delete(namespaceId, null);
    }

    /**
     * Deletes a namespace.
     *
     * @param namespaceId the ID of the namespace to delete
     * @param options     options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/namespaces.html#delete-namespace">{@code DELETE /v1/namespace/:id}</a>
     */
    public ServerResponse<Void> delete(final String namespaceId, @Nullable final WriteOptions options)
            throws IOException, NomadException {

        return executeServerAction(delete(uri("/v1/namespace/" + namespaceId), options), null);
    }

    /**
     * Queries a namespace.
     *
     * @param name name of the namespace.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/namespaces.html#read-namespace">{@code GET /v1/namespace/:id}</a>
     */
    public ServerQueryResponse<Namespace> info(final String name) throws IOException, NomadException {
        return info(name, null);
    }

    /**
     * Queries a namespace.
     *
     * @param name    name of the namespace.
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/namespaces.html#read-namespace">{@code GET /v1/namespace/:id}</a>
     */
    public ServerQueryResponse<Namespace> info(
            final String name,
            @Nullable final QueryOptions<Namespace> options
    ) throws IOException, NomadException {
        return executeServerQuery("/v1/namespace/" + name, options, NomadJson.parserFor(Namespace.class));
    }

    /**
     * Lists all namespaces.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/namespaces.html#list-namespaces">{@code GET /v1/namespaces}</a>
     */
    public ServerQueryResponse<List<Namespace>> list() throws IOException, NomadException {
        return list(null, null);
    }

    /**
     * Lists all namespaces.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/namespaces.html#list-namespaces">{@code GET /v1/namespaces}</a>
     */
    public ServerQueryResponse<List<Namespace>> list(
            @Nullable final QueryOptions<List<Namespace>> options
    ) throws IOException, NomadException {

        return list(null, options);
    }

    /**
     * Lists namespaces.
     *
     * @param namePrefix a name prefix that, if given,
     *                   restricts the results to only namespaces having a name with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/namespaces.html#list-namespaces">{@code GET /v1/namespaces}</a>
     */
    public ServerQueryResponse<List<Namespace>> list(@Nullable final String namePrefix)
            throws IOException, NomadException {

        return list(namePrefix, null);
    }

    /**
     * Lists namespaces.
     *
     * @param namePrefix a name prefix that, if given,
     *                   restricts the results to only namespaces having a name with this prefix
     * @param options    options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/namespaces.html#list-namespaces">{@code GET /v1/namespaces}</a>
     */
    public ServerQueryResponse<List<Namespace>> list(
            @Nullable final String namePrefix,
            @Nullable final QueryOptions<List<Namespace>> options
    ) throws IOException, NomadException {

        return executeServerQueryForPrefixFilteredList(
                "/v1/namespaces",
                namePrefix,
                options,
                NomadJson.parserForSortedListOf(Namespace.class, NEWEST_TO_OLDEST));
    }

    /**
     * Registers or updates a namespace.
     *
     * @param namespace the namespace to register
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/namespaces.html#create-or-update-a-namespace">{@code PUT /v1/namespace}</a>
     */
    public ServerResponse<Void> register(Namespace namespace) throws IOException, NomadException {
        return register(namespace, null);
    }

    /**
     * Registers or updates a namespace.
     *
     * @param namespace the namespace to register
     * @param options   options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/namespaces.html#create-or-update-a-namespace">{@code PUT /v1/namespace}</a>
     */
    public ServerResponse<Void> register(Namespace namespace, @Nullable WriteOptions options)
            throws IOException, NomadException {
        return executeServerAction(put("/v1/namespace", namespace, options), null);
    }

}
