package com.sebastian_daschner.scalable_coffee_shop.barista.boundary;

import com.sebastian_daschner.scalable_coffee_shop.barista.control.CoffeeBrews;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewFinished;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewStarted;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeDelivered;
import com.sebastian_daschner.scalable_coffee_shop.events.control.EventProducer;
import com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderInfo;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

public class BaristaService {

    @Inject
    EventProducer eventProducer;

    @Inject
    CoffeeBrews coffeeBrews;

    @Inject
    Logger logger;

    void makeCoffee(final OrderInfo orderInfo) {
        eventProducer.publish(new CoffeeBrewStarted(orderInfo));
    }

    void checkCoffee() {
        final Collection<UUID> unfinishedBrews = coffeeBrews.getUnfinishedBrews();
        logger.info("checking " + unfinishedBrews.size() + " unfinished brews");
        unfinishedBrews.forEach(i -> {
            if (new Random().nextBoolean())
                eventProducer.publish(new CoffeeBrewFinished(i));
        });
    }

    void checkCustomerDelivery() {
        final Collection<UUID> undeliveredOrder = coffeeBrews.getUndeliveredOrders();
        logger.info("checking " + undeliveredOrder.size() + " un-served orders");
        undeliveredOrder.forEach(i -> {
            if (new Random().nextBoolean())
                eventProducer.publish(new CoffeeDelivered(i));
        });
    }

}
