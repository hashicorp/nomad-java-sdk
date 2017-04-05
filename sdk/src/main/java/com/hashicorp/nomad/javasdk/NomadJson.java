package com.hashicorp.nomad.javasdk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashicorp.nomad.apimodel.Job;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.UPPER_CAMEL_CASE;

/**
 * Serialises Nomad API model types to JSON, and vice versa.
 * <p>
 * You are encourage to use the {@code toString()} and {@code fromJson()} on the classes in
 * the {@code com.hashicorp.nomad.apimodel} package directly, rather than using this class.
 */
public abstract class NomadJson {

    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setPropertyNamingStrategy(UPPER_CAMEL_CASE)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    /**
     * Reads a <a href="https://www.nomadproject.io/docs/http/json-jobs.html">JSON job spec</a>.
     *
     * @throws IOException if there the JSON ends unexpectedly
     */
    public static Job readJobSpec(String jobSpec) throws IOException {
        return OBJECT_MAPPER.readValue(jobSpec, JobSpec.class).job;
    }

    /**
     * Writes a <a href="https://www.nomadproject.io/docs/http/json-jobs.html">JSON job spec</a>.
     * @throws JsonProcessingException if there is a problem with the JSON
     */
    public static String asJobSpec(Job job) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(new JobSpec(job));
    }

    /**
     * Serializes a Nomad API object to JSON.
     * <p>
     * You are encourage to use the {@code toString()} method on objects of the classes in
     * the {@code com.hashicorp.nomad.apimodel} package directly, rather than using this method.
     */
    public static String serialize(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RequestSerializationException("Unable to serialize request entity: " + e, e);
        }
    }

    /**
     * Deserializes a Nomad API object from a JSON object.
     * <p>
     * You are encourage to use the {@code fromJson()} method on the classes in
     * the {@code com.hashicorp.nomad.apimodel} package directly, rather than using this method.
     *
     * @throws IOException if the JSON can't be deserialized
     */
    public static <T> T deserialize(String json, Class<T> clazz) throws IOException {
        return deserialize(json, OBJECT_MAPPER.constructType(clazz));
    }

    /**
     * Deserializes a list of Nomad API objects from a JSON array.
     * <p>
     * You are encourage to use the {@code fromJsonArray()} method on the classes in
     * the {@code com.hashicorp.nomad.apimodel} package directly, rather than using this method.
     *
     * @throws IOException if the JSON can't be deserialized
     */
    public static <T> List<T> deserializeList(String json, Class<T> clazz) throws IOException {
        return deserialize(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    static <T> T deserialize(String json, JavaType javaType) throws IOException {
        return OBJECT_MAPPER.readValue(json, javaType);
    }

    static <T> JsonParser<T> parserFor(JavaType valueType) {
        return new JsonParser<T>(valueType);
    }

    static <T> JsonParser<T> parserFor(Class<T> clazz) {
        return parserFor(OBJECT_MAPPER.constructType(clazz));
    }

    static <T> JsonParser<List<T>> parserForListOf(Class<T> elementType) {
        return parserFor(OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, elementType));
    }

    static <T> ValueExtractor<List<T>> parserForSortedListOf(
            final Class<T> elementType, final Comparator<T> comparator) {
        final JsonParser<List<T>> parser = parserForListOf(elementType);
        return new ValueExtractor<List<T>>() {
            @Override
            public List<T> extractValue(String json) throws ResponseParsingException {
                List<T> list = parser.extractValue(json);
                Collections.sort(list, comparator);
                return list;
            }
        };
    }

    private static class JobSpec {
        @JsonProperty("Job")
        public final Job job; // Checkstyle suppress VisibilityModifier

        @JsonCreator
        JobSpec(@JsonProperty("Job") Job job) {
            this.job = job;
        }
    }
}
