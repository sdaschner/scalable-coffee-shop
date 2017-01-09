package com.sebastian_daschner.scalable_coffee_shop.barista.boundary;

import com.sebastian_daschner.scalable_coffee_shop.barista.control.BaristaEventStore;
import com.sebastian_daschner.scalable_coffee_shop.barista.control.CoffeeBrews;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewFinished;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewStarted;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeDelivered;
import com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderInfo;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

public class BaristaService {

    @Inject
    BaristaEventStore eventStore;

    @Inject
    CoffeeBrews coffeeBrews;

    void makeCoffee(final OrderInfo orderInfo) {
        eventStore.addAndFire(new CoffeeBrewStarted(orderInfo));
    }

    void checkCoffee() {
        final Collection<UUID> unfinishedBrews = coffeeBrews.getUnfinishedBrews();
        System.out.println("checking " + unfinishedBrews.size() + " unfinished brews");
        unfinishedBrews.forEach(i -> {
            if (new Random().nextBoolean())
                eventStore.addAndFire(new CoffeeBrewFinished(i));
        });
    }

    void checkCustomerDelivery() {
        final Collection<UUID> undeliveredOrder = coffeeBrews.getUndeliveredOrders();
        System.out.println("checking " + undeliveredOrder.size() + " un-served orders");
        undeliveredOrder.forEach(i -> {
            if (new Random().nextBoolean())
                eventStore.addAndFire(new CoffeeDelivered(i));
        });
    }

}
