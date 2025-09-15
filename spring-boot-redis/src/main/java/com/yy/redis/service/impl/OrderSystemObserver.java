package com.yy.redis.service.impl;

import com.yy.redis.domain.PaymentEvent;
import com.yy.redis.service.PaymentObserver;
import org.springframework.stereotype.Service;

// 具体观察者：订单系统
@Service
public class OrderSystemObserver implements PaymentObserver {
    @Override
    public void onPaymentEvent(PaymentEvent event) {

    }
}