package com.sebastian_daschner.scalable_coffee_shop.beans.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Logger;

@Startup
@Singleton
public class BeanUpdateConsumer {

    @Resource
    ManagedExecutorService mes;

    @Inject
    Properties kafkaProperties;

    @Inject
    Event<CoffeeEvent> events;

    @Inject
    Logger logger;
    private Consumer<String, CoffeeEvent> consumer;

    @PostConstruct
    private void init() {
        kafkaProperties.put("group.id", "beans-consumer-" + kafkaProperties.getProperty("group-offset"));

        consumer = new KafkaConsumer<>(kafkaProperties);
        consumer.subscribe(Collections.singleton("beans"));

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

    @PreDestroy
    public void close() {
        consumer.close();
    }

}
