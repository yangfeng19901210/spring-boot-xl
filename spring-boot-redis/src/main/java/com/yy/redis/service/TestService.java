package com.yy.redis.service;

/**
 * 测试service
 *
 * @ClassName TestService
 * @Author yangfeng
 * @Date 2025/8/22 17:08
 * @Version 1.0
 */
public interface TestService {
    void createOrder(String id);
    boolean processPayment(String orderId);
}
