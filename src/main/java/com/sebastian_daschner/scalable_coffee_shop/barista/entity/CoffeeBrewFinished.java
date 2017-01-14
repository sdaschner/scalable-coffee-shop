package com.sebastian_daschner.scalable_coffee_shop.barista.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import java.util.UUID;

public class CoffeeBrewFinished extends AbstractEvent {

    private final UUID orderId;

    public CoffeeBrewFinished(final UUID orderId) {
        super();
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
