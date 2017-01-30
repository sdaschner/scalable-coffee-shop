package com.sebastian_daschner.scalable_coffee_shop.events.entity;

import javax.json.JsonObject;
import java.time.Instant;

public class BeansStored extends CoffeeEvent {

    private final String beanOrigin;
    private final int amount;

    public BeansStored(final String beanOrigin, final int amount) {
        this.beanOrigin = beanOrigin;
        this.amount = amount;
    }

    public BeansStored(final String beanOrigin, final int amount, final Instant instant) {
        super(instant);
        this.beanOrigin = beanOrigin;
        this.amount = amount;
    }

    public BeansStored(JsonObject jsonObject) {
        this(jsonObject.getString("beanOrigin"), jsonObject.getInt("amount"), Instant.parse(jsonObject.getString("instant")));
    }

    public String getBeanOrigin() {
        return beanOrigin;
    }

    public int getAmount() {
        return amount;
    }

}
