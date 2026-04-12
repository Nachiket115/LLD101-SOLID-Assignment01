package com.example.ratelimiter;

import java.util.Objects;

public final class FixedWindowStrategy implements RateLimitStrategy {
    private final RateLimitConfig config;
    private final TimeProvider timeProvider;
    private final RequestStore requestStore;

    public FixedWindowStrategy(RateLimitConfig config, TimeProvider timeProvider, RequestStore requestStore) {
        this.config = Objects.requireNonNull(config, "config");
        this.timeProvider = Objects.requireNonNull(timeProvider, "timeProvider");
        this.requestStore = Objects.requireNonNull(requestStore, "requestStore");
    }

    @Override
    public boolean allowRequest(String clientId) {
        Objects.requireNonNull(clientId, "clientId");
        long nowMillis = timeProvider.currentTimeMillis();
        long windowStartMillis = windowStart(nowMillis);

        synchronized (requestStore) {
            requestStore.cleanupExpired(nowMillis, config.getWindowSizeMillis());
            ClientRequestTracker tracker = requestStore.getOrCreate(clientId, windowStartMillis);

            if (tracker.getWindowStartMillis() != windowStartMillis) {
                tracker.reset(windowStartMillis);
            }

            if (tracker.getRequestCount() >= config.getMaxRequests()) {
                return false;
            }

            tracker.increment();
            return true;
        }
    }

    private long windowStart(long nowMillis) {
        return nowMillis - (nowMillis % config.getWindowSizeMillis());
    }
}
