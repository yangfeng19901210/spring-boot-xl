package com.yy.redis.service;

import com.yy.redis.domain.PaymentEvent;

// 观察者接口
public interface PaymentObserver {
    void onPaymentEvent(PaymentEvent event);
}