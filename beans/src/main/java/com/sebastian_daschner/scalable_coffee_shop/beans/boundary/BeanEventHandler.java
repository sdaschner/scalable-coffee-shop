package com.sebastian_daschner.scalable_coffee_shop.beans.boundary;

import com.sebastian_daschner.scalable_coffee_shop.beans.control.EventConsumer;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeBrewStarted;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderPlaced;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class BeanEventHandler {

    private EventConsumer eventConsumer;

    @Resource
    ManagedExecutorService mes;

    @Inject
    Properties kafkaProperties;

    @Inject
    Event<CoffeeEvent> events;

    @Inject
    BeanCommandService beanService;

    @Inject
    Logger logger;

    public void handle(@Observes OrderPlaced event) {
        beanService.validateBeans(event.getOrderInfo().getBeanOrigin(), event.getOrderInfo().getOrderId());
    }

    public void handle(@Observes CoffeeBrewStarted event) {
        beanService.fetchBeans(event.getOrderInfo().getBeanOrigin());
    }

    @PostConstruct
    private void initConsumer() {
        kafkaProperties.put("group.id", "beans-handler");

        eventConsumer = new EventConsumer(kafkaProperties, ev -> {
            logger.info("firing = " + ev);
            events.fire(ev);
        }, "order", "barista");

        mes.execute(eventConsumer);
    }

    @PreDestroy
    public void closeConsumer() {
        eventConsumer.stop();
    }

}
