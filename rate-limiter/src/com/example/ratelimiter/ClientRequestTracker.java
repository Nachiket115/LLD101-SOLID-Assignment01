package com.example.ratelimiter;

public final class ClientRequestTracker {
    private long windowStartMillis;
    private int requestCount;

    public ClientRequestTracker(long windowStartMillis) {
        this.windowStartMillis = windowStartMillis;
        this.requestCount = 0;
    }

    public boolean isExpired(long nowMillis, long windowSizeMillis) {
        return nowMillis >= windowStartMillis + windowSizeMillis;
    }

    public void reset(long newWindowStartMillis) {
        this.windowStartMillis = newWindowStartMillis;
        this.requestCount = 0;
    }

    public void increment() {
        requestCount++;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public long getWindowStartMillis() {
        return windowStartMillis;
    }
}
