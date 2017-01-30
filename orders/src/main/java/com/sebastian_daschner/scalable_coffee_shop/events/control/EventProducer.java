package com.sebastian_daschner.scalable_coffee_shop.events.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;
import java.util.logging.Logger;

@ApplicationScoped
public class EventProducer {

    private Producer<String, CoffeeEvent> producer;

    @Inject
    Properties kafkaProperties;

    @Inject
    Logger logger;

    @PostConstruct
    private void init() {
        producer = new KafkaProducer<>(kafkaProperties);
    }

    public void publish(CoffeeEvent event) {
        final ProducerRecord<String, CoffeeEvent> record = new ProducerRecord<>("order", event);
        logger.info("publishing = " + record);
        producer.send(record);
        producer.flush();
    }

    @PreDestroy
    public void close() {
        producer.close();
    }

}

