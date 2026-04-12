package com.example.ratelimiter;

public interface RateLimitStrategy {
    boolean allowRequest(String clientId);
}
