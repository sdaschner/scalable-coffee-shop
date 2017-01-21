package com.sebastian_daschner.scalable_coffee_shop.events.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;
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

    private Producer<String, AbstractEvent> producer;

    @Inject
    Properties kafkaProperties;

    @Inject
    Logger logger;

    @PostConstruct
    private void init() {
        producer = new KafkaProducer<>(kafkaProperties);
    }

    public void publish(AbstractEvent event) {
        final ProducerRecord<String, AbstractEvent> record = new ProducerRecord<>(event.getTopic(), event);
        logger.info("publishing = " + record);
        producer.send(record);
        producer.flush();
    }

    @PreDestroy
    public void close() {
        producer.close();
    }

}

