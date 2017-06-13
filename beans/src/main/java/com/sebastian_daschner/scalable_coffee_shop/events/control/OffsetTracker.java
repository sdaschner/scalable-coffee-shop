package com.sebastian_daschner.scalable_coffee_shop.events.control;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class OffsetTracker {

    private final Map<String, Long> topicPartitionOffsets = new ConcurrentHashMap<>();

    public void trackOffset(String topic, int partition, long offset) {
        String key = calculateKey(topic, partition);
        topicPartitionOffsets.put(key, offset);
    }

    public long nextOffset(String topic, int partition) {
        String key = calculateKey(topic, partition);
        return topicPartitionOffsets.getOrDefault(key, 0L);
    }

    private String calculateKey(String topic, int partition) {
        return topic + partition;
    }

}
