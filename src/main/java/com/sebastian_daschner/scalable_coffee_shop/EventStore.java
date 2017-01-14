package com.sebastian_daschner.scalable_coffee_shop;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.Live;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.IndexTreeList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
@Startup
public class EventStore {

    private final Map<String, IndexTreeList<Object>> eventLogs = new HashMap<>();
    private final Map<String, IndexTreeList<Object>> unhandledEvents = new HashMap<>();

    private DB mapDb;

    @Inject
    Event<UnhandledEvent> unhandled;

    @Live
    @Inject
    Event<AbstractEvent> events;

    @PostConstruct
    private void init() {
        final File dbLocation = Paths.get("/tmp/events.mapdb").toFile();
        mapDb = DBMaker.fileDB(dbLocation).transactionEnable().make();
    }

    public void initTopic(String topic) {
        final IndexTreeList<Object> eventLog = mapDb.indexTreeList(topic).createOrOpen();
        final IndexTreeList<Object> unhandledList = mapDb.indexTreeList(topic + "-unhandled").createOrOpen();

        eventLogs.put(topic, eventLog);
        unhandledEvents.put(topic, unhandledList);

        if (!eventLog.isEmpty())
            System.out.println("there are events in topic " + topic);
        eventLog.forEach(c -> System.out.println(c.getClass().getSimpleName()));

        unhandled.fire(new UnhandledEvent(topic));
    }

    @Lock
    public void addAndFire(final AbstractEvent event, final String topic) {
        final IndexTreeList<Object> events = eventLogs.get(topic);
        final IndexTreeList<Object> unhandledList = unhandledEvents.get(topic);
        if (events == null || unhandledList == null)
            throw new IllegalArgumentException("Unknown event topic " + topic);

        events.add(event);
        unhandledList.add(event);
        mapDb.commit();

        unhandled.fire(new UnhandledEvent(topic));
    }

    @Asynchronous
    public void sentUnhandledEvents(@Observes UnhandledEvent event) {
        final Iterator<Object> iterator = unhandledEvents.get(event.topic).iterator();
        while (iterator.hasNext()) {
            AbstractEvent unhandled = (AbstractEvent) iterator.next();
            events.fire(unhandled);
            iterator.remove();
            mapDb.commit();
        }
    }

    @Lock(LockType.READ)
    public List<AbstractEvent> getEvents(String topic) {
        return getEvents(topic, 0);
    }

    @Lock(LockType.READ)
    public List<AbstractEvent> getEvents(String topic, final int lastVersion) {
        final IndexTreeList<Object> events = eventLogs.get(topic);
        if (events == null)
            throw new IllegalArgumentException("Unknown event topic " + topic);

        return events.subList(lastVersion, events.size()).stream()
                .map(e -> (AbstractEvent) e)
                .collect(Collectors.toList());
    }

    @PreDestroy
    public void close() {
        mapDb.close();
    }

    private static class UnhandledEvent {
        private final String topic;

        private UnhandledEvent(final String topic) {
            this.topic = topic;
        }
    }

}
