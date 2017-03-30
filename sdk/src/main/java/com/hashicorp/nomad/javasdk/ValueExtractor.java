package com.hashicorp.nomad.javasdk;

interface ValueExtractor<T> {
    T extractValue(String string) throws ResponseParsingException, ErrorFoundInResponseEntityException;

    ValueExtractor<String> RAW_STRING = new ValueExtractor<String>() {
        @Override
        public String extractValue(String string) {
            return string;
        }
    };
}
