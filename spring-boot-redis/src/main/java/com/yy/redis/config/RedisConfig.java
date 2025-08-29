package com.yy.redis.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Configuration
@EnableCaching // 启用缓存支持
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory factory,
            ObjectMapper objectMapper // 注入全局 ObjectMapper
    ) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // Key 序列化为字符串
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // Value 使用 GenericJackson2JsonRedisSerializer
        GenericJackson2JsonRedisSerializer serializer =
                new GenericJackson2JsonRedisSerializer(objectMapper); // ✅ 构造函数注入

        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory,
                                     ObjectMapper objectMapper // 注入全局 ObjectMapper
    ) {
        // 配置序列化规则
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60)) // 全局缓存过期时间 30 分钟
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                )
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper))
                )
                .disableCachingNullValues(); // 禁止缓存 null 值

        // 支持为不同缓存空间设置独立过期时间（可选）
        return RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                /**
                 * 在 Spring Cache 注解中，value 或 cacheNames 属性用于标识缓存数据的逻辑分组，在如下的配置中users相当于给缓存定义了
                 * 一个逻辑分组，所有使用该缓存分组的接口过期时间都会是应用如下代码设置的过期时间
                 * @Cacheable(value = "users", key = "#id") // 指定缓存名称为 "users"
                 * public User getUser(Long id) { ... }
                 * 在如上的两行代码中通过value指定缓存分组为users表示该方法的缓存数据属于名为 users 的缓存空间
                 * 所有使用 @Cacheable(value = "users") 注解的方法，其缓存数据将应用此处指定的 TTL（如 240小时)
                 */
                .withCacheConfiguration("users", config.entryTtl(Duration.ofHours(240))) // 自定义 users 缓存过期时间
                .build();
    }

}