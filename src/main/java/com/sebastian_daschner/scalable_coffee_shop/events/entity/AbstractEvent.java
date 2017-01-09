package com.sebastian_daschner.scalable_coffee_shop.events.entity;

import java.io.Serializable;
import java.time.Instant;

public class AbstractEvent implements Serializable {

    private final Instant instant = Instant.now();

    public Instant getInstant() {
        return instant;
    }

}
