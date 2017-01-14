package com.sebastian_daschner.scalable_coffee_shop.beans.boundary;

import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewStarted;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.Live;
import com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderPlaced;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Singleton
public class BeanEventHandler {

    @Inject
    BeanService beanService;

    public void handle(@Observes @Live OrderPlaced event) {
        beanService.validateBeans(event.getOrderInfo().getBeanOrigin(), event.getOrderInfo().getOrderId());
    }

    public void handle(@Observes @Live CoffeeBrewStarted event) {
        beanService.fetchBeans(event.getOrderInfo().getBeanOrigin());
    }

}
