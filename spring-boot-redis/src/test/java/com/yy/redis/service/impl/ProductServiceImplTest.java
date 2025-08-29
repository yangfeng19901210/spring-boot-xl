package com.yy.redis.service.impl;

import com.yy.redis.domain.Product;
import com.yy.redis.service.ProductService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceImplTest {
    @Resource
    private ProductService productService;
    @Test
    void testAdd(){
        Product product = new Product();
        product.setName("测试产品");
        product.setCategory("测试类别");
        product.setPrice(new BigDecimal("125.0"));
        product.setStockQuantity(100);
        product.setDescription("这是一个测试产品");
        product.setIsAvailable(1);
        productService.save(product);

    }
    @Test
    void testGetById(){
        Product product = productService.selectById(2);
        System.out.println(product);
    }

}