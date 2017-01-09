package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import java.util.UUID;

public class OrderFinished extends AbstractEvent {

    private final UUID orderId;

    public OrderFinished(final UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
