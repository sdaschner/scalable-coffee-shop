package com.sebastian_daschner.scalable_coffee_shop.beans.control;

import com.sebastian_daschner.scalable_coffee_shop.barista.entity.BeansFetched;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.BeansStored;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
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

    @PostConstruct
    private void init() {
        eventStore.getEvents().forEach(this::apply);
    }

    public int getRemainingAmount(final String beanOrigin) {
        return beanOrigins.getOrDefault(beanOrigin, 0);
    }

    @Asynchronous
    public void apply(@Observes BeansStored beansStored) {
        beanOrigins.merge(beansStored.getBeanOrigin(), beansStored.getAmount(), Math::addExact);
    }

    @Asynchronous
    public void apply(@Observes BeansFetched beansFetched) {
        beanOrigins.merge(beansFetched.getBeanOrigin(), 0, (i1, i2) -> i1 - 1);
    }

    private void apply(final AbstractEvent event) {
        if (event instanceof BeansFetched)
            apply((BeansFetched) event);
        else if (event instanceof BeansStored)
            apply((BeansStored) event);
    }

}
