package com.yy.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * redis服务启动类
 * @ClassName RedisApplication
 * @Author yangfeng
 * @Date 2025/8/22 16:55
 * @Version 1.0
 */
@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}