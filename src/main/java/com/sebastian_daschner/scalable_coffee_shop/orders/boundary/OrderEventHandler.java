package com.sebastian_daschner.scalable_coffee_shop.orders.boundary;

import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewFinished;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewStarted;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeDelivered;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.OrderBeansValidated;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.OrderFailedBeansNotAvailable;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.HandledBy;
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

import static com.sebastian_daschner.scalable_coffee_shop.events.entity.HandledBy.Group.ORDER_HANDLER;
import static java.util.Arrays.asList;

@Singleton
@Startup
public class OrderEventHandler {

    @Resource
    ManagedExecutorService mes;

    @Inject
    Properties kafkaProperties;

    @Inject
    @HandledBy(ORDER_HANDLER)
    Event<AbstractEvent> events;

    @Inject
    OrderService orderService;

    @Inject
    Logger logger;

    public void handle(@Observes @HandledBy(ORDER_HANDLER) OrderBeansValidated event) {
        orderService.acceptOrder(event.getOrderId());
    }

    public void handle(@Observes @HandledBy(ORDER_HANDLER) OrderFailedBeansNotAvailable event) {
        orderService.cancelOrder(event.getOrderId(), "No beans of the origin were available");
    }

    public void handle(@Observes @HandledBy(ORDER_HANDLER) CoffeeBrewStarted event) {
        orderService.startOrder(event.getOrderInfo().getOrderId());
    }

    public void handle(@Observes @HandledBy(ORDER_HANDLER) CoffeeBrewFinished event) {
        orderService.finishOrder(event.getOrderId());
    }

    public void handle(@Observes @HandledBy(ORDER_HANDLER) CoffeeDelivered event) {
        orderService.deliverOrder(event.getOrderId());
    }

    @PostConstruct
    private void initConsumer() {
        kafkaProperties.put("group.id", "order-handler");

        KafkaConsumer<String, AbstractEvent> consumer = new KafkaConsumer<>(kafkaProperties);
        consumer.subscribe(asList("beans", "barista"));

        mes.execute(() -> consumeEvent(consumer));
    }

    private void consumeEvent(final KafkaConsumer<String, AbstractEvent> consumer) {
        ConsumerRecords<String, AbstractEvent> records = consumer.poll(Long.MAX_VALUE);
        for (ConsumerRecord<String, AbstractEvent> record : records) {
            logger.info("firing = " + record.value());
            events.fire(record.value());
        }
        consumer.commitSync();
        mes.execute(() -> consumeEvent(consumer));
    }

}
