package com.sebastian_daschner.scalable_coffee_shop.barista.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

public class BeansFetched extends AbstractEvent {

    private final String beanOrigin;

    public BeansFetched(final String beanOrigin) {
        this.beanOrigin = beanOrigin;
    }

    public String getBeanOrigin() {
        return beanOrigin;
    }

}
