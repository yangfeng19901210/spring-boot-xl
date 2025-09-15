package com.yy.redis.service;

import com.yy.redis.domain.Order;
import com.yy.redis.domain.PaymentResult;

// 支付策略接口
public interface PaymentStrategy {
    PaymentResult pay(Order order);
    PaymentResult refund(Order order);
    boolean verify(Order order);
    String getProvider();
}