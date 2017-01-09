package com.sebastian_daschner.scalable_coffee_shop.orders.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;
import org.mapdb.DB;
import org.mapdb.IndexTreeList;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Startup
public class OrderEventStore {

    private IndexTreeList<Object> eventLog;

    @Inject
    DB mapDB;

    @Inject
    Event<AbstractEvent> events;

    @PostConstruct
    private void init() {
        eventLog = mapDB.indexTreeList("order-events").createOrOpen();

        if (!eventLog.isEmpty())
            System.out.println("there are order events:");
        eventLog.forEach(c -> System.out.println(c.getClass().getSimpleName()));
    }

    @Lock
    public void addAndFire(final AbstractEvent event) {
        eventLog.add(event);
        mapDB.commit();
        events.fire(event);
    }

    @Lock(LockType.READ)
    public List<AbstractEvent> getEvents() {
        return getEvents(0);
    }

    @Lock(LockType.READ)
    public List<AbstractEvent> getEvents(final int lastVersion) {
        return eventLog.subList(lastVersion, eventLog.size()).stream()
                .map(e -> (AbstractEvent) e)
                .collect(Collectors.toList());
    }

}
