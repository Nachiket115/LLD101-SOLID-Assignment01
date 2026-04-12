package com.example.ratelimiter;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        RateLimitConfig config = new RateLimitConfig(3, 10_000);
        ManualTimeProvider timeProvider = new ManualTimeProvider(0);
        RequestStore requestStore = new RequestStore();

        RateLimiter rateLimiter = new RateLimiter(
                new FixedWindowStrategy(config, timeProvider, requestStore)
        );

        String clientId = "client-1";

        System.out.println("Limit: 3 requests per 10 seconds");
        System.out.println("Request 1 allowed=" + rateLimiter.allowRequest(clientId));
        System.out.println("Request 2 allowed=" + rateLimiter.allowRequest(clientId));
        System.out.println("Request 3 allowed=" + rateLimiter.allowRequest(clientId));
        System.out.println("Request 4 allowed=" + rateLimiter.allowRequest(clientId));

        System.out.println("Advancing time by 10 seconds...");
        timeProvider.advanceMillis(10_000);

        System.out.println("Request 5 allowed=" + rateLimiter.allowRequest(clientId));
        System.out.println("Store size after cleanup=" + requestStore.size());
    }
}
