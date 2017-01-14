package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

public class OrderAccepted extends AbstractEvent {

    private final OrderInfo orderInfo;

    public OrderAccepted(final OrderInfo orderInfo) {
        super();
        this.orderInfo = orderInfo;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

}
