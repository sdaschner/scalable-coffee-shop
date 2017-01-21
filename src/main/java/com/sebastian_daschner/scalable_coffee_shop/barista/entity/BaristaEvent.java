package com.sebastian_daschner.scalable_coffee_shop.barista.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import javax.json.bind.annotation.JsonbTransient;
import java.time.Instant;

public abstract class BaristaEvent extends AbstractEvent {

    protected BaristaEvent() {
    }

    protected BaristaEvent(final Instant instant) {
        super(instant);
    }

    @JsonbTransient
    @Override
    public String getTopic() {
        return "barista";
    }

}
