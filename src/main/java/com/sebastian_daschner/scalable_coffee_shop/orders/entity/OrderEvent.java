package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import javax.json.bind.annotation.JsonbTransient;
import java.time.Instant;

public abstract class OrderEvent extends AbstractEvent {

    protected OrderEvent() {
    }

    protected OrderEvent(final Instant instant) {
        super(instant);
    }

    @Override
    @JsonbTransient
    public String getTopic() {
        return "order";
    }

}
