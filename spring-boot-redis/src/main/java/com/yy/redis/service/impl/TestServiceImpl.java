package com.yy.redis.service.impl;

import com.yy.redis.service.TestService;
import com.yy.redis.utils.RedissonLockUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 替换描述
 *
 * @ClassName TestServiceImpl
 * @Author yangfeng
 * @Date 2025/8/22 17:09
 * @Version 1.0
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {
    public void createOrder(String id) {
        log.info("service创建订单{}",Thread.currentThread().getId());
        // 调用锁方法，无返回值时使用 Runnable
        RedissonLockUtil.lockRun(
                "order:create:" + id, // 锁名称（业务语义化）
                5l,            // 等待3秒
                -10l, TimeUnit.SECONDS,             // 看门狗自动续期（leaseTime=-1）
                () -> {
                    // 加锁后的业务逻辑
                    query(id);
                }
        );
    }

    private void query(String id){

        log.info("线程{}于{}执行订单查询{}",Thread.currentThread().getId(), LocalDateTime.now(),id);
        try {
            Thread.sleep(1000*3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("查询完成");
    }
    public boolean processPayment(String orderId) {
        // 调用锁方法，通过 Supplier 返回布尔值
        return RedissonLockUtil.lockRun(
                "payment:process:" + orderId,
                5,
                -1, TimeUnit.SECONDS,
                () -> {
                    return update(orderId); // 返回支付结果
                }
        );
    }
    private boolean update(String id){
        log.info("线程{}于{}执行订单更新{}",Thread.currentThread().getId(), LocalDateTime.now(),id);
        return false;
    }
}