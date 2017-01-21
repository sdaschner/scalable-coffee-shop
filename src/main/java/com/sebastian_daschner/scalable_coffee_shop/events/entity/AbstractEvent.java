package com.sebastian_daschner.scalable_coffee_shop.events.entity;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public abstract class AbstractEvent implements Serializable {

    @JsonbProperty
    private final Instant instant;

    protected AbstractEvent() {
        instant = Instant.now();
    }

    protected AbstractEvent(final Instant instant) {
        Objects.requireNonNull(instant);
        this.instant = instant;
    }

    @JsonbTransient
    public abstract String getTopic();

    public Instant getInstant() {
        return instant;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractEvent that = (AbstractEvent) o;

        return instant.equals(that.instant);
    }

    @Override
    public int hashCode() {
        return instant.hashCode();
    }

}
