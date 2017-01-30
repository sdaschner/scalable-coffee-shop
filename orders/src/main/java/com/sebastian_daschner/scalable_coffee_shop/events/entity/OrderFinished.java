package com.sebastian_daschner.scalable_coffee_shop.events.entity;

import javax.json.JsonObject;
import java.time.Instant;
import java.util.UUID;

public class OrderFinished extends CoffeeEvent {

    private final UUID orderId;

    public OrderFinished(final UUID orderId) {
        this.orderId = orderId;
    }

    public OrderFinished(final UUID orderId, Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public OrderFinished(JsonObject jsonObject) {
        this(UUID.fromString(jsonObject.getString("orderId")), Instant.parse(jsonObject.getString("instant")));
    }

    public UUID getOrderId() {
        return orderId;
    }

}
