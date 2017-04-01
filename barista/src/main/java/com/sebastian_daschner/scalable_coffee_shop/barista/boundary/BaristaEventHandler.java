package com.sebastian_daschner.scalable_coffee_shop.barista.boundary;

import com.sebastian_daschner.scalable_coffee_shop.events.control.EventConsumer;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderAccepted;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Properties;
import java.util.logging.Logger;

@Singleton
@Startup
public class BaristaEventHandler {

    private EventConsumer eventConsumer;

    @Resource
    ManagedExecutorService mes;

    @Inject
    Properties kafkaProperties;

    @Inject
    Event<CoffeeEvent> events;

    @Inject
    BaristaCommandService baristaService;

    @Inject
    Logger logger;

    public void handle(@Observes OrderAccepted event) {
        baristaService.makeCoffee(event.getOrderInfo());
    }

    @PostConstruct
    private void init() {
        kafkaProperties.put("group.id", "barista-handler");

        eventConsumer = new EventConsumer(kafkaProperties, ev -> {
            logger.info("firing = " + ev);
            events.fire(ev);
        }, "order");

        mes.execute(eventConsumer);
    }

}
