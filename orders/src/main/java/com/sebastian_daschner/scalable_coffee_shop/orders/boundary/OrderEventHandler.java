package com.sebastian_daschner.scalable_coffee_shop.orders.boundary;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

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

import static java.util.Arrays.asList;

@Singleton
@Startup
public class OrderEventHandler {

    @Resource
    ManagedExecutorService mes;

    @Inject
    Properties kafkaProperties;

    @Inject
    Event<CoffeeEvent> events;

    @Inject
    OrderCommandService orderService;

    @Inject
    Logger logger;

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

    @PostConstruct
    private void initConsumer() {
        kafkaProperties.put("group.id", "order-handler");

        Consumer<String, CoffeeEvent> consumer = new KafkaConsumer<>(kafkaProperties);
        consumer.subscribe(asList("beans", "barista"));

        mes.execute(() -> consumeEvent(consumer));
    }

    private void consumeEvent(final Consumer<String, CoffeeEvent> consumer) {
        ConsumerRecords<String, CoffeeEvent> records = consumer.poll(Long.MAX_VALUE);
        for (ConsumerRecord<String, CoffeeEvent> record : records) {
            logger.info("firing = " + record.value());
            events.fire(record.value());
        }
        consumer.commitSync();
        mes.execute(() -> consumeEvent(consumer));
    }

}
