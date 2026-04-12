package com.example.distributedcache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class CacheNode {
    private final String nodeId;
    private final int capacity;
    private final Map<String, String> data;
    private final EvictionPolicy evictionPolicy;

    public CacheNode(String nodeId, int capacity, EvictionPolicy evictionPolicy) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Cache node capacity must be positive.");
        }
        this.nodeId = Objects.requireNonNull(nodeId, "nodeId");
        this.capacity = capacity;
        this.evictionPolicy = Objects.requireNonNull(evictionPolicy, "evictionPolicy");
        this.data = new LinkedHashMap<>();
    }

    public String getNodeId() {
        return nodeId;
    }

    public synchronized String get(String key) {
        String value = data.get(key);
        if (value != null) {
            evictionPolicy.keyAccessed(key);
        }
        return value;
    }

    public synchronized void put(String key, String value) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");

        if (data.containsKey(key)) {
            data.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (data.size() >= capacity) {
            String evictedKey = evictionPolicy.evictKey();
            data.remove(evictedKey);
        }

        data.put(key, value);
        evictionPolicy.keyAdded(key);
    }

    public synchronized boolean containsKey(String key) {
        return data.containsKey(key);
    }

    public synchronized int size() {
        return data.size();
    }

    public synchronized Map<String, String> snapshot() {
        return Map.copyOf(data);
    }

    @Override
    public synchronized String toString() {
        return nodeId + " size=" + size() + " data=" + data;
    }
}
