package com.sebastian_daschner.scalable_coffee_shop.events.entity;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Qualifies events that are handled by a (Kafka) group.
 * These events must not interfere with other events of other topics or other group handlers.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface HandledBy {

    /**
     * The group name (may be a Kafka group id).
     */
    Group value();

    /**
     * The available groups. Included to avoid too many string literals.
     */
    enum Group {
        BEANS_CONSUMER, BEANS_HANDLER,
        ORDER_CONSUMER, ORDER_HANDLER,
        BARISTA_CONSUMER, BARISTA_HANDLER
    }

}
