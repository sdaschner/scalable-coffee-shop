package com.sebastian_daschner.scalable_coffee_shop.beans.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import javax.json.bind.annotation.JsonbTransient;
import java.time.Instant;

public abstract class BeansEvent extends AbstractEvent {

    protected BeansEvent() {
    }

    protected BeansEvent(final Instant instant) {
        super(instant);
    }

    @Override
    @JsonbTransient
    public String getTopic() {
        return "beans";
    }

}
