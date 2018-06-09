package com.sebastian_daschner.scalable_coffee_shop.events.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.ProducerFencedException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class EventProducer {

    private Producer<String, CoffeeEvent> producer;
    private String topic;

    @Inject
    Properties kafkaProperties;

    @Inject
    Logger logger;

    @PostConstruct
    private void init() {
        kafkaProperties.put("transactional.id", UUID.randomUUID().toString());
        producer = new KafkaProducer<>(kafkaProperties);
        topic = kafkaProperties.getProperty("beans.topic");
        producer.initTransactions();
    }

    public void publish(CoffeeEvent... events) {
        try {
            producer.beginTransaction();
            send(events);
            producer.commitTransaction();
        } catch (ProducerFencedException e) {
            producer.close();
        } catch (KafkaException e) {
            producer.abortTransaction();
        }
    }

    private void send(CoffeeEvent... events) {
        for (final CoffeeEvent event : events) {
            final ProducerRecord<String, CoffeeEvent> record = new ProducerRecord<>(topic, event);
            logger.info("publishing = " + record);
            producer.send(record);
        }
    }

    @PreDestroy
    public void close() {
        producer.close();
    }

}

