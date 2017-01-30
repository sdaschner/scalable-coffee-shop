package com.sebastian_daschner.scalable_coffee_shop.events.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.util.Properties;

@ApplicationScoped
public class KafkaConfigurator {

    private Properties kafkaProperties;

    @PostConstruct
    private void initProperties() {
        try {
            kafkaProperties = new Properties();
            kafkaProperties.load(KafkaConfigurator.class.getResourceAsStream("/kafka.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Produces
    @RequestScoped
    public Properties exposeKafkaProperties() throws IOException {
        final Properties properties = new Properties();
        properties.putAll(kafkaProperties);
        return properties;
    }

}
