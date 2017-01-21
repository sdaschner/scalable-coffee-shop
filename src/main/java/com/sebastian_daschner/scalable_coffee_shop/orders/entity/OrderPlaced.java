package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import javax.json.JsonObject;
import java.time.Instant;

public class OrderPlaced extends OrderEvent {

    private final OrderInfo orderInfo;

    public OrderPlaced(final OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public OrderPlaced(final OrderInfo orderInfo, Instant instant) {
        super(instant);
        this.orderInfo = orderInfo;
    }

    public OrderPlaced(JsonObject jsonObject) {
        this(new OrderInfo(jsonObject.getJsonObject("orderInfo")), Instant.parse(jsonObject.getString("instant")));
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

}
