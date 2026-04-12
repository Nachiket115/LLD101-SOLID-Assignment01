package com.example.ratelimiter;

import java.util.Objects;

public final class RateLimiter {
    private final RateLimitStrategy strategy;

    public RateLimiter(RateLimitStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    public boolean allowRequest(String clientId) {
        return strategy.allowRequest(clientId);
    }
}
