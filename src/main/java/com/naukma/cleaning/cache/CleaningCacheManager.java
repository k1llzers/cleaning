package com.naukma.cleaning.cache;

import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CleaningCacheManager implements CacheManager {

    private static Logger logger = LoggerFactory.getLogger(CleaningCacheManager.class);
    private final ConcurrentMap<String, Cache> cacheMap;

    public CleaningCacheManager(@NotNull String... cacheNames) {
        this.cacheMap = new ConcurrentHashMap<>();
        for(String name : cacheNames) {
            this.cacheMap.put(name, new ConcurrentMapCache(name));
        }
    }

    @Override
    public Cache getCache(String name) {
        return this.cacheMap.get(name);
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(this.cacheMap.keySet());
    }

    //86400000 millis == 1 day
    @Scheduled(fixedRate = 86400000)
    public void clearAllCaches() {
        for(Cache cache : this.cacheMap.values()) {
            cache.clear();
        }
        logger.info("All caches cleared");
    }

}
