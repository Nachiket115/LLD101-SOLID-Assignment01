package com.example.distributedcache;

import java.util.LinkedHashMap;
import java.util.Map;

public final class InMemoryDatabase implements Database {
    private final Map<String, String> records = new LinkedHashMap<>();

    @Override
    public synchronized String get(String key) {
        return records.get(key);
    }

    @Override
    public synchronized void put(String key, String value) {
        records.put(key, value);
    }
}
