package com.hashicorp.nomad.javasdk;

/**
 * A predicate representing a boolean property.
 *
 * @param <T> The type on which the predicate can be evaluated
 */
public interface Predicate<T> {

    /**
     * Evaluates the predicate.
     *
     * @param value the value to check the predicate against
     * @return true if the value satisfies the predicate, false if it does not
     */
    boolean apply(T value);
}
