package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import java.util.UUID;

public class OrderCancelled extends AbstractEvent {

    private final UUID orderId;
    private final String reason;

    public OrderCancelled(final UUID orderId, final String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

}
