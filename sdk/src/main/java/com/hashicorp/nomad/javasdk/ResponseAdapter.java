package com.hashicorp.nomad.javasdk;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * Creates a specific type of {@link NomadResponse}
 * from an async-http-client response.
 *
 * This abstract class is used by package-private methods that allow endpoint implementations to be
 * consistent and concise.
 *
 * @param <T> type extracted from response bodies
 * @param <R> type of response created
 */
abstract class ResponseAdapter<T, R extends NomadResponse<T>> {
    private final ValueExtractor<T> valueExtractor;

    protected ResponseAdapter(@Nullable ValueExtractor<T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    public R apply(HttpResponse httpResponse)
            throws IOException, ResponseParsingException, ErrorFoundInResponseEntityException {

        String rawEntity = EntityUtils.toString(httpResponse.getEntity());
        T value = valueExtractor == null
                ? null
                : valueExtractor.extractValue(rawEntity);
        return buildResponse(httpResponse, rawEntity, value);
    }

    protected abstract R buildResponse(HttpResponse httpResponse, String rawEntity, @Nullable T value);
}
