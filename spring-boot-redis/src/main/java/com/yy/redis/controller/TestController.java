package com.yy.redis.controller;

import com.yy.redis.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 * @ClassName TestController
 * @Author yangfeng
 * @Date 2025/8/25 11:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
@Slf4j
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    @PostMapping("/createOrder")
    public String createOrder(){
        log.info("创建订单{}",Thread.currentThread().getId());
        testService.createOrder("123");
        return "test";
    }


}