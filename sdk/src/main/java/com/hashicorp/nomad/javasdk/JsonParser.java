package com.hashicorp.nomad.javasdk;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;

import java.io.IOException;
import java.nio.ByteBuffer;

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

    /**
     * Parses as many values as possible.
     *
     * @param buffer   the data to parse
     * @param callback invoked once for each value parsed
     * @return the buffer containing any unparsed bytes, or {@code null} if the data was fully consumed.
     * @throws ResponseParsingException if an error occurs during parsing
     */
    public ByteBuffer parseRepeatedly(ByteBuffer buffer, Callback<T> callback) throws NomadException {
        JsonLocation checkpoint = null;
        try {
            com.fasterxml.jackson.core.JsonParser jacksonParser = NomadJson.OBJECT_MAPPER.getFactory()
                    .createParser(buffer.array(), buffer.position(), buffer.remaining());
            while (jacksonParser.nextToken() != null) {
                callback.apply(NomadJson.OBJECT_MAPPER.<T>readValue(jacksonParser, responseEntityType));
                checkpoint = jacksonParser.getTokenLocation();
            }
        } catch (IOException e) {
            if (e instanceof JsonProcessingException && e.getMessage().startsWith("Unexpected end-of-input")) {
                if (checkpoint != null)
                    buffer.position((int) checkpoint.getByteOffset());
                return buffer;
            } else {
                throw new ResponseParsingException(
                        "Unable to parse next StreamFrame from response body JSON: " + e,
                        null,
                        e);
            }
        }
        return null;
    }
}
