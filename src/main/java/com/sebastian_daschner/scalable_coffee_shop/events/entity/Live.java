package com.sebastian_daschner.scalable_coffee_shop.events.entity;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Events that happened live, i.e. no replay events applied later on.
 * Only these events trigger subsequent commands.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Live {
}
