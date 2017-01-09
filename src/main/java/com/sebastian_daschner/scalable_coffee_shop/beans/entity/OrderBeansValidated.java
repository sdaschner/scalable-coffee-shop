package com.sebastian_daschner.scalable_coffee_shop.beans.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import java.util.UUID;

public class OrderBeansValidated extends AbstractEvent {

    private final UUID orderId;

    public OrderBeansValidated(final UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
