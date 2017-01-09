package com.sebastian_daschner.scalable_coffee_shop.barista.boundary;

import com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderAccepted;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Singleton
@Asynchronous
public class BaristaEventHandler {

    @Inject
    BaristaService baristaService;

    public void handle(@Observes OrderAccepted event) {
        baristaService.makeCoffee(event.getOrderInfo());
    }

}
