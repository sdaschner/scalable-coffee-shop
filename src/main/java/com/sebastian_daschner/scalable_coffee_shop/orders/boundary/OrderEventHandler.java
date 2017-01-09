package com.sebastian_daschner.scalable_coffee_shop.orders.boundary;

import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewFinished;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewStarted;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeDelivered;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.OrderBeansValidated;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.OrderFailedBeansNotAvailable;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Singleton
@Asynchronous
public class OrderEventHandler {

    @Inject
    OrderService orderService;

    public void handle(@Observes OrderBeansValidated event) {
        orderService.acceptOrder(event.getOrderId());
    }

    public void handle(@Observes OrderFailedBeansNotAvailable event) {
        orderService.cancelOrder(event.getOrderId(), "No beans of the origin were available");
    }

    public void handle(@Observes CoffeeBrewStarted event) {
        orderService.startOrder(event.getOrderInfo().getOrderId());
    }

    public void handle(@Observes CoffeeBrewFinished event) {
        orderService.finishOrder(event.getOrderId());
    }

    public void handle(@Observes CoffeeDelivered event) {
        orderService.deliverOrder(event.getOrderId());
    }

}
