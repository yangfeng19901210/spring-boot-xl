package com.yy.redis.service;

import com.yy.redis.domain.Order;
import com.yy.redis.domain.PaymentResult;

// 支付流程模板
public abstract class AbstractPaymentProcessor {
    // 模板方法：定义支付流程
    public final PaymentResult processPayment(Order order) {
        validate(order);
        preProcess(order);
        PaymentResult result = executePayment(order);
        postProcess(order, result);
        return result;
    }
    
    protected abstract void validate(Order order);
    protected abstract void preProcess(Order order);
    protected abstract PaymentResult executePayment(Order order);
    protected abstract void postProcess(Order order, PaymentResult result);
}