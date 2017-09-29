package com.hashicorp.nomad.javasdk;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Map;

/**
 * Abstract parent class for API objects that allows them to preserve properties they don't explicitly model.
 */
public abstract class ApiObject {

    private Map<String, Object> unmappedProperties;

    /**
     * Gets all properties that aren't mapped to Java bean properties.
     * <p>
     * The primary purpose of this method is to provide a mechanism for unknown properties to remain intact after
     * a round-trip from JSON to this class and back again. If you find yourself needing to read or write a property
     * that is not yet modelled as a bean property, consider looking for a newer version of the SDK.
     */
    @JsonAnyGetter
    public Map<String, Object> getUnmappedProperties() {
        return unmappedProperties;
    }

    /**
     * Sets a property that isn't mapped to a Java bean property.
     * <p>
     * The primary purpose of this method is to provide a mechanism for unknown properties to remain intact after
     * a round-trip from JSON to this class and back again. If you find yourself needing to read or write a property
     * that is not yet modelled as a bean property, consider looking for a newer version of the SDK.
     *
     * @param name  name of the property
     * @param value value of the property
     */
    @JsonAnySetter
    public void addUnmappedProperty(String name, Object value) {
        if (this.unmappedProperties == null)
            this.unmappedProperties = new java.util.HashMap<>();
        unmappedProperties.put(name, value);
    }
}
