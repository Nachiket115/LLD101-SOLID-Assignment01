package com.example.ratelimiter;

public final class RateLimitConfig {
    private final int maxRequests;
    private final long windowSizeMillis;

    public RateLimitConfig(int maxRequests, long windowSizeMillis) {
        if (maxRequests <= 0) {
            throw new IllegalArgumentException("Max requests must be positive.");
        }
        if (windowSizeMillis <= 0) {
            throw new IllegalArgumentException("Window size must be positive.");
        }
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSizeMillis() {
        return windowSizeMillis;
    }
}
