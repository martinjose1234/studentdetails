package com.studentdetails.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

public class CacheConfig {
	
	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
		return (builder) -> builder
				.withCacheConfiguration("itemCache",
						RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
				.withCacheConfiguration("customerCache",
						RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
	}
}
