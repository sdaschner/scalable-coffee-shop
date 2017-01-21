package com.sebastian_daschner.scalable_coffee_shop.barista.entity;

import javax.json.JsonObject;
import java.time.Instant;

public class BeansFetched extends BaristaEvent {

    private final String beanOrigin;

    public BeansFetched(final String beanOrigin) {
        this.beanOrigin = beanOrigin;
    }

    public BeansFetched(final String beanOrigin, final Instant instant) {
        super(instant);
        this.beanOrigin = beanOrigin;
    }

    public BeansFetched(final JsonObject jsonObject) {
        this(jsonObject.getString("beanOrigin"), Instant.parse(jsonObject.getString("instant")));

    }

    public String getBeanOrigin() {
        return beanOrigin;
    }

}
