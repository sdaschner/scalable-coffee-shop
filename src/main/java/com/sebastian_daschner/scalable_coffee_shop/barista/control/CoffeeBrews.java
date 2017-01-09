package com.sebastian_daschner.scalable_coffee_shop.barista.control;

import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewFinished;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewStarted;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeDelivered;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

import static java.util.Collections.unmodifiableCollection;

/**
 * Contains the coffee brew aggregated information.
 * Handles, dispatches & applies internal events.
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CoffeeBrews {

    private final Set<UUID> unfinishedBrews = new ConcurrentSkipListSet<>();
    private final Set<UUID> undeliveredOrders = new ConcurrentSkipListSet<>();

    @Inject
    BaristaEventStore eventStore;

    public Collection<UUID> getUnfinishedBrews() {
        return unmodifiableCollection(unfinishedBrews);
    }

    public Collection<UUID> getUndeliveredOrders() {
        return unmodifiableCollection(undeliveredOrders);
    }

    @PostConstruct
    private void init() {
        eventStore.getEvents().forEach(this::apply);
    }

    @Asynchronous
    public void apply(@Observes CoffeeBrewStarted event) {
        unfinishedBrews.add(event.getOrderInfo().getOrderId());
    }

    @Asynchronous
    public void apply(@Observes CoffeeBrewFinished event) {
        final Iterator<UUID> iterator = unfinishedBrews.iterator();
        while (iterator.hasNext()) {
            final UUID orderId = iterator.next();
            if (orderId.equals(event.getOrderId())) {
                iterator.remove();
                undeliveredOrders.add(orderId);
            }
        }
    }

    @Asynchronous
    public void apply(@Observes CoffeeDelivered event) {
        undeliveredOrders.removeIf(i -> i.equals(event.getOrderId()));
    }

    private void apply(final AbstractEvent event) {
        if (event instanceof CoffeeBrewStarted)
            apply((CoffeeBrewStarted) event);
        else if (event instanceof CoffeeBrewFinished)
            apply((CoffeeBrewFinished) event);
        else if (event instanceof CoffeeDelivered)
            apply((CoffeeDelivered) event);
    }

}
