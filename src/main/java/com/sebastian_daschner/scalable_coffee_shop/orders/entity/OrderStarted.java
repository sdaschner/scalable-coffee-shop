package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import javax.json.JsonObject;
import java.time.Instant;
import java.util.UUID;

public class OrderStarted extends OrderEvent {

    private final UUID orderId;

    public OrderStarted(final UUID orderId) {
        this.orderId = orderId;
    }

    public OrderStarted(final UUID orderId, final Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public OrderStarted(final JsonObject jsonObject) {
        this(UUID.fromString(jsonObject.getString("orderId")), Instant.parse(jsonObject.getString("instant")));
    }

    public UUID getOrderId() {
        return orderId;
    }

}
