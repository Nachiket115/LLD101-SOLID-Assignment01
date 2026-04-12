package com.example.distributedcache;

public interface EvictionPolicy {
    void keyAccessed(String key);

    void keyAdded(String key);

    void keyRemoved(String key);

    String evictKey();
}
