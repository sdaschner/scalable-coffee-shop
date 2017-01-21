package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import javax.json.JsonObject;
import java.time.Instant;
import java.util.UUID;

public class OrderCancelled extends OrderEvent {

    private final UUID orderId;
    private final String reason;

    public OrderCancelled(final UUID orderId, final String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }

    public OrderCancelled(final UUID orderId, final String reason, Instant instant) {
        super(instant);
        this.orderId = orderId;
        this.reason = reason;
    }

    public OrderCancelled(JsonObject jsonObject) {
        this(UUID.fromString(jsonObject.getString("orderId")), jsonObject.getString("reason"), Instant.parse(jsonObject.getString("instant")));
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

}
