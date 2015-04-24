package com.github.rakawestu.explorejogja.app.dependencyinjection.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author rakawm
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ApplicationContext {
    String value() default "";
}