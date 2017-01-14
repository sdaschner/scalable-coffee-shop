package com.sebastian_daschner.scalable_coffee_shop.beans.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

public class BeansStored extends AbstractEvent {

    private final String beanOrigin;
    private final int amount;

    public BeansStored(final String beanOrigin, final int amount) {
        super();
        this.beanOrigin = beanOrigin;
        this.amount = amount;
    }

    public String getBeanOrigin() {
        return beanOrigin;
    }

    public int getAmount() {
        return amount;
    }

}
