package com.sebastian_daschner.scalable_coffee_shop.events.entity;

import javax.json.JsonObject;
import java.time.Instant;

public class OrderAccepted extends CoffeeEvent {

    private final OrderInfo orderInfo;

    public OrderAccepted(final OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public OrderAccepted(final OrderInfo orderInfo, Instant instant) {
        super(instant);
        this.orderInfo = orderInfo;
    }

    public OrderAccepted(JsonObject jsonObject) {
        this(new OrderInfo(jsonObject.getJsonObject("orderInfo")), Instant.parse(jsonObject.getString("instant")));
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

}
