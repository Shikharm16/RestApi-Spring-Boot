package com.employee.memberinfo.Redis;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.employee.memberinfo.Controller.EmployeeDTO.EmployeeGetDTO;

@Configuration
public class RedisConf {
	
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
      
    @Bean
    public RedisTemplate<String, EmployeeGetDTO> redisTemplate() {
        final RedisTemplate<String, EmployeeGetDTO> redisTemplate = new RedisTemplate<String,EmployeeGetDTO>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<EmployeeGetDTO>(EmployeeGetDTO.class));
        return redisTemplate;
    }
    
    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
            configurationMap.put("employee", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(100)));  
//            configurationMap.put("cache2", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(2)));     
            builder.withInitialCacheConfigurations(configurationMap);
        };
    }

}

