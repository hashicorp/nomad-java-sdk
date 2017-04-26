package com.hashicorp.nomad.javasdk;

/**
 * A method that extracts a strongly typed value from a string.
 *
 * This interface is used by package-private methods that allow endpoint implementations to be consistent and concise.
 *
 * @param <T> type extracted from the string
 */
interface ValueExtractor<T> {
    T extractValue(String string) throws ResponseParsingException, ErrorFoundInResponseEntityException;

    ValueExtractor<String> RAW_STRING = new ValueExtractor<String>() {
        @Override
        public String extractValue(String string) {
            return string;
        }
    };
}
