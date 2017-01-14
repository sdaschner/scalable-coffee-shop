package com.sebastian_daschner.scalable_coffee_shop.barista.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;
import com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderInfo;

public class CoffeeBrewStarted extends AbstractEvent {

    private final OrderInfo orderInfo;

    public CoffeeBrewStarted(final OrderInfo orderInfo) {
        super();
        this.orderInfo = orderInfo;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

}
