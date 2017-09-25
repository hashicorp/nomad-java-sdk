package com.hashicorp.nomad.javasdk;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a test method that should be ignored when not testing against NomadEnterprise.
 *
 * By default, such methods will be ignored by {@link ApiTestRunner},
 * but they will be run if the "NomadEnterprise" system property is set
 * (e.g. by passing `-DNomadEnterprise` on the java command line).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RequiresNomadEnterprise {}
