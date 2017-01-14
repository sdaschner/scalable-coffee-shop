package com.sebastian_daschner.scalable_coffee_shop.barista.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import java.util.UUID;

public class CoffeeDelivered extends AbstractEvent {

    private final UUID orderId;

    public CoffeeDelivered(final UUID orderId) {
        super();
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
