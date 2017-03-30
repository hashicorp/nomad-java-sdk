package com.hashicorp.nomad.javasdk;

/**
 * A predicate representing a boolean property.
 *
 * @param <T> The type on which the predicate can be evaluated
 */
public interface Predicate<T> {

    /**
     * Applies the predicate to the given value.
     */
    boolean apply(T value);
}
