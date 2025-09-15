package com.yy.redis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付成功返回类
 * @ClassName PaymentResult
 * @Author yangfeng
 * @Date 2025/9/15 10:34
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResult {
    private boolean success;
    private String message;
    private String orderId;
}