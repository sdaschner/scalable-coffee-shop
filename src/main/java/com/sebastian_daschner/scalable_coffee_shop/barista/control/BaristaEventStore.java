package com.sebastian_daschner.scalable_coffee_shop.barista.control;

import com.sebastian_daschner.scalable_coffee_shop.EventStore;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BaristaEventStore {

    private static final String TOPIC = "barista-events";

    @Inject
    EventStore eventStore;

    // TODO add saved snapshots

    @PostConstruct
    private void init() {
        eventStore.initTopic(TOPIC);
    }

    public void addAndFire(final AbstractEvent event) {
        eventStore.addAndFire(event, TOPIC);
    }

    public List<AbstractEvent> getEvents() {
        return eventStore.getEvents(TOPIC);
    }

    public List<AbstractEvent> getEvents(final int lastVersion) {
        return eventStore.getEvents(TOPIC, lastVersion);
    }

}
