package com.sebastian_daschner.scalable_coffee_shop.beans.entity;

import javax.json.JsonObject;
import java.time.Instant;
import java.util.UUID;

public class OrderBeansValidated extends BeansEvent {

    private final UUID orderId;

    public OrderBeansValidated(final UUID orderId) {
        this.orderId = orderId;
    }

    public OrderBeansValidated(final UUID orderId, final Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public OrderBeansValidated(JsonObject jsonObject) {
        this(UUID.fromString(jsonObject.getString("orderId")), Instant.parse(jsonObject.getString("instant")));
    }

    public UUID getOrderId() {
        return orderId;
    }

}
