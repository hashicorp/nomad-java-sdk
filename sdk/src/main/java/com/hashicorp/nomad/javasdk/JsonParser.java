package com.hashicorp.nomad.javasdk;

import com.fasterxml.jackson.databind.JavaType;

import java.io.IOException;

/**
 * Wraps a Jackson ObjectMapper to parse values of a specific type.
 * @param <T>
 */
class JsonParser<T> implements ValueExtractor<T> {
    private final JavaType responseEntityType;

    JsonParser(JavaType responseEntityType) {
        this.responseEntityType = responseEntityType;
    }

    /**
     * Parses a value from the JSON string.
     */
    @Override
    public T extractValue(String json) throws ResponseParsingException {
        try {
            return NomadJson.deserialize(json, responseEntityType);
        } catch (IOException e) {
            throw new ResponseParsingException(
                    "Unable to parse " + responseEntityType + " from response body JSON: " + json,
                    json,
                    e);
        }
    }
}
