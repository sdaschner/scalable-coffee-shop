package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import java.util.UUID;

public class OrderDelivered extends AbstractEvent {

    private final UUID orderId;

    public OrderDelivered(final UUID orderId) {
        super();
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
