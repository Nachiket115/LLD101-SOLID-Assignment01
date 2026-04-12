package com.example.distributedcache;

import java.util.LinkedHashSet;

public final class LruEvictionPolicy implements EvictionPolicy {
    private final LinkedHashSet<String> accessOrder = new LinkedHashSet<>();

    @Override
    public synchronized void keyAccessed(String key) {
        accessOrder.remove(key);
        accessOrder.add(key);
    }

    @Override
    public synchronized void keyAdded(String key) {
        keyAccessed(key);
    }

    @Override
    public synchronized void keyRemoved(String key) {
        accessOrder.remove(key);
    }

    @Override
    public synchronized String evictKey() {
        if (accessOrder.isEmpty()) {
            throw new IllegalStateException("No key available for eviction.");
        }
        String key = accessOrder.iterator().next();
        accessOrder.remove(key);
        return key;
    }
}
