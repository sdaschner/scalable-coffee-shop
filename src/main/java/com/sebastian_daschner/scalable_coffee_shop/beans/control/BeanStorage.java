package com.sebastian_daschner.scalable_coffee_shop.beans.control;

import com.sebastian_daschner.scalable_coffee_shop.barista.entity.BeansFetched;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.BeansStored;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Aggregate. Stores the available bean origins.
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class BeanStorage {

    private Map<String, Integer> beanOrigins = new ConcurrentHashMap<>();

    @Inject
    BeansEventStore eventStore;

    @Inject
    Event<AbstractEvent> replayEvents;

    @PostConstruct
    private void init() {
        eventStore.getEvents().forEach(replayEvents::fire);
    }

    public Map<String, Integer> getStoredBeans() {
        return Collections.unmodifiableMap(beanOrigins);
    }

    public int getRemainingAmount(final String beanOrigin) {
        return beanOrigins.getOrDefault(beanOrigin, 0);
    }

    public void apply(@Observes BeansStored beansStored) {
        beanOrigins.merge(beansStored.getBeanOrigin(), beansStored.getAmount(), Math::addExact);
    }

    public void apply(@Observes BeansFetched beansFetched) {
        beanOrigins.merge(beansFetched.getBeanOrigin(), 0, (i1, i2) -> i1 - 1);
    }

}
