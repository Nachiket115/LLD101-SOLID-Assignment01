package com.example.distributedcache;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class DistributedCache {
    private final List<CacheNode> nodes;
    private final DistributionStrategy distributionStrategy;
    private final Database database;

    public DistributedCache(
            int numberOfNodes,
            int nodeCapacity,
            DistributionStrategy distributionStrategy,
            Database database
    ) {
        if (numberOfNodes <= 0) {
            throw new IllegalArgumentException("Number of cache nodes must be positive.");
        }
        this.nodes = new ArrayList<>();
        for (int i = 1; i <= numberOfNodes; i++) {
            nodes.add(new CacheNode("CacheNode-" + i, nodeCapacity, new LruEvictionPolicy()));
        }
        this.distributionStrategy = Objects.requireNonNull(distributionStrategy, "distributionStrategy");
        this.database = Objects.requireNonNull(database, "database");
    }

    public String get(String key) {
        CacheNode node = distributionStrategy.selectNode(key, nodes);
        String value = node.get(key);
        if (value != null) {
            return value;
        }

        String databaseValue = database.get(key);
        if (databaseValue != null) {
            node.put(key, databaseValue);
        }
        return databaseValue;
    }

    public void put(String key, String value) {
        CacheNode node = distributionStrategy.selectNode(key, nodes);
        node.put(key, value);
        database.put(key, value);
    }

    public void printNodeStatus() {
        for (CacheNode node : nodes) {
            System.out.println(node);
        }
    }
}
