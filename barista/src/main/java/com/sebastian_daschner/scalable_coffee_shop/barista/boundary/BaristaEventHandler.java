package com.sebastian_daschner.scalable_coffee_shop.barista.boundary;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderAccepted;
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
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Logger;

@Singleton
@Startup
public class BaristaEventHandler {

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

        Consumer<String, CoffeeEvent> consumer = new KafkaConsumer<>(kafkaProperties);
        consumer.subscribe(Collections.singleton("order"));

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
