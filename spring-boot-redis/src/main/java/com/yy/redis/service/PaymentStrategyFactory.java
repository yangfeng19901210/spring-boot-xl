package com.yy.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 支付策略工厂
@Service
@Slf4j
public class PaymentStrategyFactory {
    private final Map<String, PaymentStrategy> strategies = new ConcurrentHashMap<>();
    
    @Autowired
    public PaymentStrategyFactory(List<PaymentStrategy> strategyList) {
        for (PaymentStrategy strategy : strategyList) {
            log.info("注册支付策略: {}", strategy.getClass().getSimpleName());
            strategies.put(strategy.getProvider(), strategy);
        }
    }
    
    public PaymentStrategy getStrategy(String paymentProvider) {
        PaymentStrategy strategy = strategies.get(paymentProvider);
        if (strategy == null) {
            throw new IllegalArgumentException("不支持的支付方式: " + paymentProvider);
        }
        return strategy;
    }
}