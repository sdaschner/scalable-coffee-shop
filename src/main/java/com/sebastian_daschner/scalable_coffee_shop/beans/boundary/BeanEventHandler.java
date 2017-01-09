package com.sebastian_daschner.scalable_coffee_shop.beans.boundary;

import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewStarted;
import com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderPlaced;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Singleton
@Asynchronous
public class BeanEventHandler {

    @Inject
    BeanService beanService;

    public void handle(@Observes OrderPlaced event) {
        beanService.validateBeans(event.getOrderInfo().getBeanOrigin(), event.getOrderInfo().getOrderId());
    }

    public void handle(@Observes CoffeeBrewStarted event) {
        beanService.fetchBeans(event.getOrderInfo().getBeanOrigin());
    }

}
