package com.example.distributedcache;

import java.util.List;

public interface DistributionStrategy {
    CacheNode selectNode(String key, List<CacheNode> nodes);
}
