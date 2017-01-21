package com.sebastian_daschner.scalable_coffee_shop.orders.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.HandledBy;
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
import javax.inject.Inject;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Logger;

import static com.sebastian_daschner.scalable_coffee_shop.events.entity.HandledBy.Group.ORDER_CONSUMER;

@Startup
@Singleton
public class OrderUpdateConsumer {

    @Resource
    ManagedExecutorService mes;

    @Inject
    Properties kafkaProperties;

    @Inject
    @HandledBy(ORDER_CONSUMER)
    Event<AbstractEvent> events;

    @Inject
    Logger logger;

    @PostConstruct
    private void init() {
        kafkaProperties.put("group.id", "order-consumer-" + kafkaProperties.getProperty("group-offset"));

        Consumer<String, AbstractEvent> consumer = new KafkaConsumer<>(kafkaProperties);
        consumer.subscribe(Collections.singleton("order"));

        mes.execute(() -> consumeEvent(consumer));
    }

    private void consumeEvent(final Consumer<String, AbstractEvent> consumer) {
        ConsumerRecords<String, AbstractEvent> records = consumer.poll(Long.MAX_VALUE);
        for (ConsumerRecord<String, AbstractEvent> record : records) {
            logger.info("firing = " + record.value());
            events.fire(record.value());
        }
        consumer.commitSync();

        mes.execute(() -> consumeEvent(consumer));
    }

}
