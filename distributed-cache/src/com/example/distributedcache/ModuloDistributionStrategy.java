package com.example.distributedcache;

import java.util.List;
import java.util.Objects;

public final class ModuloDistributionStrategy implements DistributionStrategy {
    @Override
    public CacheNode selectNode(String key, List<CacheNode> nodes) {
        Objects.requireNonNull(key, "key");
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("At least one cache node is required.");
        }
        int index = Math.floorMod(key.hashCode(), nodes.size());
        return nodes.get(index);
    }
}
