package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AclToken;
import com.hashicorp.nomad.apimodel.AclTokenListStub;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * API for managing ACL tokens,
 * exposing the <a href="https://www.nomadproject.io/api/acl-tokens.html">ACL tokens</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class AclTokensApi extends ApiBase {

    AclTokensApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Bootstraps the ACL system and returns the initial management token.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#bootstrap-token">{@code PUT /v1/acl/bootstrap}</a>
     */
    public ServerResponse<AclToken> bootstrap() throws IOException, NomadException {
        return bootstrap(null);
    }

    /**
     * Bootstraps the ACL system and returns the initial management token.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#bootstrap-token">{@code PUT /v1/acl/bootstrap}</a>
     */
    public ServerResponse<AclToken> bootstrap(@Nullable WriteOptions options) throws IOException, NomadException {
        return executeServerAction(put("/v1/acl/bootstrap", options), NomadJson.parserFor(AclToken.class));
    }

    /**
     * Creates an ACL token, returning a token with a server-assigned accessor ID and secret ID.
     *
     * @param token a token with no accessor ID
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#create-token">{@code PUT /v1/acl/token}</a>
     */
    public ServerResponse<AclToken> create(AclToken token)
            throws IOException, NomadException {
        return create(token, null);
    }

    /**
     * Creates an ACL token, returning a token with a server-assigned accessor ID and secret ID.
     *
     * @param token   a token with no accessor ID
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#create-token">{@code PUT /v1/acl/token}</a>
     */
    public ServerResponse<AclToken> create(AclToken token, @Nullable WriteOptions options)
            throws IOException, NomadException {
        if (token.getAccessorId() != null && !token.getAccessorId().isEmpty()) {
            throw new java.lang.IllegalArgumentException("Cannot create an ACL token that already has an accessor ID");
        }
        return executeServerAction(put("/v1/acl/token", token, options), NomadJson.parserFor(AclToken.class));
    }

    /**
     * Deletes an ACL token.
     *
     * @param accessorId accessorId of the token to delete
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-token.html#delete-token">{@code DELETE /v1/acl/token/:accessor_id}</a>
     */
    public ServerResponse<Void> delete(final String accessorId) throws IOException, NomadException {
        return delete(accessorId, null);
    }

    /**
     * Deletes an ACL token.
     *
     * @param accessorId accessorId of the token to delete
     * @param options    options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-token.html#delete-token">{@code DELETE /v1/acl/token/:accessor_id}</a>
     */
    public ServerResponse<Void> delete(final String accessorId, @Nullable final WriteOptions options)
            throws IOException, NomadException {

        return executeServerAction(delete(uri("/v1/acl/token/" + accessorId), options), null);
    }

    /**
     * Retrieves an ACL token.
     *
     * @param accessorId accessor ID of the token.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#read-token">{@code GET /v1/acl/token/:accessor_id}</a>
     */
    public ServerQueryResponse<AclToken> info(final String accessorId) throws IOException, NomadException {
        return info(accessorId, null);
    }

    /**
     * Retrieves an ACL token.
     *
     * @param accessorId accessor ID of the token.
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#read-token">{@code GET /v1/acl/token/:accessor_id}</a>
     */
    public ServerQueryResponse<AclToken> info(
            final String accessorId,
            @Nullable final QueryOptions<AclToken> options
    ) throws IOException, NomadException {
        return executeServerQuery("/v1/acl/token/" + accessorId, options, NomadJson.parserFor(AclToken.class));
    }

    /**
     * Lists ACL tokens.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#list-tokens">{@code GET /v1/acl/tokens}</a>
     */
    public ServerQueryResponse<List<AclTokenListStub>> list() throws IOException, NomadException {
        return list(null, null);
    }

    /**
     * Lists all ACL tokens.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#list-tokens">{@code GET /v1/acl/tokens}</a>
     */
    public ServerQueryResponse<List<AclTokenListStub>> list(
            @Nullable final QueryOptions<List<AclTokenListStub>> options
    ) throws IOException, NomadException {

        return list(null, options);
    }

    /**
     * Lists ACL tokens.
     *
     * @param accessorIdPrefix an even-length accessor ID prefix that, if given,
     *                         restricts the results to only tokens having an accessor ID with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#list-tokens">{@code GET /v1/acl/tokens}</a>
     */
    public ServerQueryResponse<List<AclTokenListStub>> list(@Nullable final String accessorIdPrefix)
            throws IOException, NomadException {

        return list(accessorIdPrefix, null);
    }

    /**
     * Lists ACL tokens.
     *
     * @param accessorIdPrefix an even-length accessor ID prefix that, if given,
     *                         restricts the results to only tokens having an accessor ID with this prefix
     * @param options          options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#list-tokens">{@code GET /v1/acl/tokens}</a>
     */
    public ServerQueryResponse<List<AclTokenListStub>> list(
            @Nullable final String accessorIdPrefix,
            @Nullable final QueryOptions<List<AclTokenListStub>> options
    ) throws IOException, NomadException {

        return executeServerQueryForPrefixFilteredList(
                "/v1/acl/tokens",
                accessorIdPrefix,
                options,
                NomadJson.parserForListOf(AclTokenListStub.class));
    }

    /**
     * Updates an ACL token.
     *
     * @param token a token with with an accessor ID
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#update-token">{@code PUT /v1/acl/token/:accessor_id}</a>
     */
    public ServerResponse<AclToken> update(AclToken token)
            throws IOException, NomadException {
        return update(token, null);
    }

    /**
     * Updates an ACL token.
     *
     * @param token   a token with with an accessor ID
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/acl-tokens.html#update-token">{@code PUT /v1/acl/token/:accessor_id}</a>
     */
    public ServerResponse<AclToken> update(AclToken token, @Nullable WriteOptions options)
            throws IOException, NomadException {

        if (token.getAccessorId() == null || token.getAccessorId().isEmpty()) {
            throw new java.lang.IllegalArgumentException("Cannot update an ACL token that doesn't have an accessor ID");
        }
        return executeServerAction(
                put("/v1/acl/token/" + token.getAccessorId(), token, options),
                NomadJson.parserFor(AclToken.class));
    }

}
