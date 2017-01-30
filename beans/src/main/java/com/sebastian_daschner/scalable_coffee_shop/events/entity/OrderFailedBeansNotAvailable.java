package com.sebastian_daschner.scalable_coffee_shop.events.entity;

import javax.json.JsonObject;
import java.time.Instant;
import java.util.UUID;

public class OrderFailedBeansNotAvailable extends CoffeeEvent {

    private final UUID orderId;

    public OrderFailedBeansNotAvailable(final UUID orderId) {
        this.orderId = orderId;
    }

    public OrderFailedBeansNotAvailable(final UUID orderId, final Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public OrderFailedBeansNotAvailable(JsonObject jsonObject) {
        this(UUID.fromString(jsonObject.getString("orderId")), Instant.parse(jsonObject.getString("instant")));
    }

    public UUID getOrderId() {
        return orderId;
    }

}
