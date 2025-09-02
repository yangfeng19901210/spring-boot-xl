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
        Product product = productService.selectById(3);
        System.out.println(product);
    }

    @Test
    void addProduct() {
        Product product = new Product();
        product.setName("测试产品2");
        product.setCategory("测试类别2");
        product.setPrice(new BigDecimal("1595.3612"));
        product.setStockQuantity(5500);
        product.setDescription("这是一个测试产品2");
        product.setIsAvailable(1);
        productService.addProduct(product);
    }

    @Test
    void updateProduct() {
        Product product = productService.selectById(4);
        product.setName("降龙十八掌");
        product.setPrice(new BigDecimal("239.10"));
        productService.updateProduct(product);
    }
}