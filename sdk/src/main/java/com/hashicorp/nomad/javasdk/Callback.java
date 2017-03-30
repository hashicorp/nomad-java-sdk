package com.hashicorp.nomad.javasdk;

/**
 * A callback that accepts values of type T.
 */
interface Callback<T> {

    /**
     * Invokes the callback with the given value.
     */
    void apply(T value) throws NomadException;
}
