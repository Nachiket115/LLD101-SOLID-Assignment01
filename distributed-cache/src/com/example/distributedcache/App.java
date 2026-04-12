package com.example.distributedcache;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        InMemoryDatabase database = new InMemoryDatabase();
        database.put("user:4", "Priya");

        DistributedCache cache = new DistributedCache(
                3,
                2,
                new ModuloDistributionStrategy(),
                database
        );

        System.out.println("PUT user:1 = Nachiket");
        cache.put("user:1", "Nachiket");

        System.out.println("PUT user:2 = Aman");
        cache.put("user:2", "Aman");

        System.out.println("PUT user:3 = Riya");
        cache.put("user:3", "Riya");

        System.out.println("GET user:1 -> " + cache.get("user:1"));
        System.out.println("GET user:4 -> " + cache.get("user:4"));
        System.out.println("GET user:9 -> " + cache.get("user:9"));

        System.out.println("PUT user:7 = Isha (triggers LRU eviction on its node)");
        cache.put("user:7", "Isha");

        cache.printNodeStatus();
    }
}
