package com.yy.redis.utils;

import com.yy.common.exception.LockInterruptedException;
import com.yy.common.exception.LockTimeoutException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 分布式锁工具类
 * @ClassName RedissonLockUtil
 * @Author yangfeng
 * @Date 2025/8/22 16:04
 * @Version 1.0
 */
@Slf4j
@Component
public class RedissonLockUtil {

    private static RedissonClient staticRedissonClient;
    @Resource
    private RedissonClient redissonClient;

    private RedissonLockUtil() {

    }


    public static <T> T lockRun(
            String lockName, long waitTime, long leaseTime,
            TimeUnit timeUnit, Supplier<T> task) {

        // 1. 参数校验
        if (StringUtils.isBlank(lockName))
            throw new IllegalArgumentException("锁名称不能为空");
        if (task == null)
            throw new IllegalArgumentException("任务不能为空");

        RLock rLock = staticRedissonClient.getLock(lockName);
        boolean acquired = false;
        try {
            log.info("线程[{}]尝试获取锁: {}", Thread.currentThread().getId(), lockName);
            // 启用看门狗（leaseTime = -1）
            acquired = rLock.tryLock(waitTime, -1, timeUnit);

            if (acquired) {
                return task.get();
            } else {
                log.warn("锁获取超时: {} (等待时间: {}{})", lockName, waitTime, timeUnit);
                throw new LockTimeoutException("资源忙，请重试");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("锁等待被中断: {}", lockName, e);
            throw new LockInterruptedException("操作中断", e);
        } finally {
            if (acquired && rLock.isHeldByCurrentThread()) {
                try {
                    rLock.unlock();
                    log.debug("锁释放成功: {}", lockName);
                } catch (IllegalMonitorStateException ex) {
                    log.warn("锁【{}】已自动释放", lockName);
                } catch (Exception e) {
                    log.error("锁【{}】释放异常", lockName, e);
                }
            }
        }
    }
    /**
     * 锁定并执行，不需要返回值
     * @param lockName
     * @param waitTime
     * @param leaseTime
     * @param timeUnit
     * @param task
     * @Return: void
     * @author: yangfeng
     * @date: 2025/8/22 16:54
     **/
    public static void lockRun(
            String lockName,
            long waitTime,
            long leaseTime,
            TimeUnit timeUnit,
            Runnable task
    ) {
        RedissonLockUtil.lockRun(
                lockName,
                waitTime,
                leaseTime,
                timeUnit,
                () -> {
                    task.run();
                    return null;
                }
        );
    }
    @PostConstruct
    public void init() {
        staticRedissonClient = redissonClient; // 实例 → 静态
    }



}