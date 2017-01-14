package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

public class OrderPlaced extends AbstractEvent {

    private final OrderInfo orderInfo;

    public OrderPlaced(final OrderInfo orderInfo) {
        super();
        this.orderInfo = orderInfo;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

}
