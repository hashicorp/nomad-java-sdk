package com.hashicorp.nomad.javasdk;

import org.apache.http.HttpResponse;

import javax.annotation.Nullable;
import java.math.BigInteger;

/**
 * A response from a Nomad server API.
 * <p>
 * The index in a ServerResponse can be used with {@link QueryOptions} in subsequent requests to perform
 * <a href="https://www.nomadproject.io/docs/http/#blocking-queries">Blocking Queries</a>.
 *
 * @param <T> type of value that was extracted from the response body
 */
public class ServerResponse<T> extends NomadResponse<T> {
    public static final String X_NOMAD_INDEX = "X-Nomad-Index";

    protected final HttpResponse httpResponse;

    /**
     * Creates a new ServerResponse.
     *
     * @param httpResponse  the HTTP response
     * @param rawEntity raw HTTP response entity as a string
     * @param value     response value extracted from the response entity
     */
    public ServerResponse(HttpResponse httpResponse, String rawEntity, @Nullable T value) {
        super(rawEntity, value);
        this.httpResponse = httpResponse;
    }

    /**
     * Returns the raw HttpResponse.
     */
    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    /**
     * Returns the value of the @{code X-Nomad-Index} header.
     *
     * @throws ResponseHeaderException if the header is missing or cannot be parsed
     * @see <a href="https://www.nomadproject.io/docs/http/#blocking-queries">Blocking Queries</a>
     */
    public BigInteger getIndex() throws ResponseHeaderException {
        String stringValue = httpResponse.getFirstHeader(X_NOMAD_INDEX).getValue();
        try {
            return new BigInteger(stringValue);
        } catch (NumberFormatException e) {
            throw ResponseHeaderException.parsing(X_NOMAD_INDEX, stringValue, e);
        }
    }
}
