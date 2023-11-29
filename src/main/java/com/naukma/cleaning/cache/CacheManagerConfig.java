package com.naukma.cleaning.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheManagerConfig {

    @Bean
    public CacheManager cacheManager() {
        return new CleaningCacheManager("proposals");
    }

}
