package com.yy.redis.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentEvent {
    private Order order;
    private PaymentResult result;
    private Date eventTime;
}