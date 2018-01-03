package com.jw.rs.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation, when an interface or a implementation is marked with current annotation, it will be registered as
 * a REST endpoint.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestApi {
}
