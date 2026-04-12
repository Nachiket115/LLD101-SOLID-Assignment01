package com.example.ratelimiter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public final class RequestStore {
    private final Map<String, ClientRequestTracker> trackers = new HashMap<>();

    public synchronized ClientRequestTracker getOrCreate(String clientId, long windowStartMillis) {
        Objects.requireNonNull(clientId, "clientId");
        return trackers.computeIfAbsent(clientId, key -> new ClientRequestTracker(windowStartMillis));
    }

    public synchronized void remove(String clientId) {
        trackers.remove(clientId);
    }

    public synchronized void cleanupExpired(long nowMillis, long windowSizeMillis) {
        Iterator<Map.Entry<String, ClientRequestTracker>> iterator = trackers.entrySet().iterator();
        while (iterator.hasNext()) {
            ClientRequestTracker tracker = iterator.next().getValue();
            if (tracker.isExpired(nowMillis, windowSizeMillis)) {
                iterator.remove();
            }
        }
    }

    public synchronized int size() {
        return trackers.size();
    }
}
