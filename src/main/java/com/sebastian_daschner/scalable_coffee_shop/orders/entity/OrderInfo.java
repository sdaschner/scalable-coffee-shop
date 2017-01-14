package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import java.util.UUID;

public class OrderInfo extends AbstractEvent {

    private final UUID orderId;
    private final CoffeeType type;
    private final String beanOrigin;

    public OrderInfo(final UUID orderId, final CoffeeType type, final String beanOrigin) {
        super();
        this.orderId = orderId;
        this.type = type;
        this.beanOrigin = beanOrigin;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public CoffeeType getType() {
        return type;
    }

    public String getBeanOrigin() {
        return beanOrigin;
    }

}
