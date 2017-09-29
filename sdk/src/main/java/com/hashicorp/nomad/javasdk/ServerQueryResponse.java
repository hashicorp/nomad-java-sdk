package com.hashicorp.nomad.javasdk;

import org.apache.http.HttpResponse;

import javax.annotation.Nullable;

/**
 * A response from a query request to a Nomad server API.
 *
 * @param <T> type of value that was deserialized from the response body
 */
public class ServerQueryResponse<T> extends ServerResponse<T> {
    public static final String X_NOMAD_LASTCONTACT = "X-Nomad-LastContact";
    public static final String X_NOMAD_KNOWNLEADER = "X-Nomad-KnownLeader";

    /**
     * Creates a new ServerQueryResponse.
     *
     * @param httpResponse the underlying HTTP response
     * @param rawEntity    the unparsed HTTP response entity (body)
     * @param value        response value extracted from the response entity
     */
    public ServerQueryResponse(HttpResponse httpResponse, String rawEntity, @Nullable T value) {
        super(httpResponse, rawEntity, value);
    }

    /**
     * Indicates whether the server had a known leader when responding to the request.
     *
     * @return true if the @{code X-Nomad-KnownLeader} header was "true", otherwise false
     * @throws ResponseHeaderException if the header is missing
     */
    public boolean hadKnownLeader() throws ResponseHeaderException {
        return "true".equals(httpResponse.getFirstHeader(X_NOMAD_KNOWNLEADER).getValue());
    }

    /**
     * Milliseconds that had elapsed on the server since it last had contact with the leader.
     * <p>
     * This can be used to gauge staleness when using {@link QueryOptions#setAllowStale(boolean)}.
     *
     * @throws ResponseHeaderException if the header is missing or cannot be parsed
     */
    public long getMillisSinceLastContact() throws ResponseHeaderException {
        String stringValue = httpResponse.getFirstHeader(X_NOMAD_LASTCONTACT).getValue();
        try {
            return Long.parseLong(stringValue);
        } catch (NumberFormatException e) {
            throw ResponseHeaderException.parsing(X_NOMAD_LASTCONTACT, stringValue, e);
        }
    }
}
