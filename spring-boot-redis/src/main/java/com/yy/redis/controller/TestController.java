package com.yy.redis.controller;

import com.yy.redis.domain.Product;
import com.yy.redis.service.ProductService;
import com.yy.redis.service.TestService;
import com.yy.redis.vo.in.AddProductInVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    private final ProductService productService;
    @PostMapping("/createOrder")
    public String createOrder(){
        log.info("创建订单{}",Thread.currentThread().getId());
        testService.createOrder("123");
        return "test";
    }
    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.selectById(id);
    }
    @PostMapping("/addProduct")
    public boolean addProduct(@RequestBody AddProductInVO vo){
        log.info("请求参数{}",vo);
        return true;
    }


}