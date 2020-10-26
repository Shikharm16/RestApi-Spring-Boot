//package com.employee.memberinfo.Controller;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//
//@Configuration
//public class RedisConf {
//
//    @Bean
//    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
//        return (builder) -> {
//            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
//            configurationMap.put("employees", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(100)));  
////            configurationMap.put("cache2", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(2)));     
//            builder.withInitialCacheConfigurations(configurationMap);
//        };
//    }
//
//}

