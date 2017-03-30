package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;

/**
 * Response from the Nomad API.
 *
 * @param <T> type of value that was deserialized from the response body
 */
public class NomadResponse<T> {
    private final String rawEntity;
    private final T value;

    /**
     * Creates a new NomadResponse.
     */
    public NomadResponse(String rawEntity, @Nullable T value) {
        this.rawEntity = rawEntity;
        this.value = value;
    }

    /**
     * @return the raw HTTP response body as a String
     */
    public String getRawEntity() {
        return rawEntity;
    }

    /**
     * @return the deserialised response value
     */
    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<Response containing "
                + rawEntity.length() + " codepoint entity: [" + getRawEntity() + "]>";
    }
}
