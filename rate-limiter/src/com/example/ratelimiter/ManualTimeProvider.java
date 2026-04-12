package com.example.ratelimiter;

public final class ManualTimeProvider implements TimeProvider {
    private long currentTimeMillis;

    public ManualTimeProvider(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }

    @Override
    public long currentTimeMillis() {
        return currentTimeMillis;
    }

    public void advanceMillis(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Cannot move time backwards.");
        }
        currentTimeMillis += millis;
    }
}
