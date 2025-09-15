package com.yy.redis.service.impl;

import com.yy.redis.domain.Order;
import com.yy.redis.domain.PaymentResult;
import com.yy.redis.service.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class AlipayStrategy implements PaymentStrategy {
    @Override
    public PaymentResult pay(Order order) {
        // 调用支付宝SDK实现支付逻辑
        return new PaymentResult(true, "支付宝支付成功", order.getOrderId());
    }
    
    @Override
    public PaymentResult refund(Order order) {
        // 调用支付宝退款接口
        return new PaymentResult(true, "支付宝退款成功", order.getOrderId());
    }
    
    @Override
    public boolean verify(Order order) {
        // 验证支付宝支付信息
        return true;
    }
    
    @Override
    public String getProvider() {
        return "Alipay";
    }
}