package com.yy.redis.service;

import com.yy.redis.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;

/**
* @author yangfeng
* @description 针对表【product(产品信息表)】的数据库操作Service
* @createDate 2025-08-29 11:57:14
*/
public interface ProductService extends IService<Product> {

    Product selectById(Integer id);

    Product addProduct(Product product);

    Product updateProduct(Product product);
    /**
     * 删除商品
     * @param id 商品id
     * @Return: boolean
     * @author: yangfeng
     * @date: 2025/9/2 10:21
     **/
    boolean deleteProduct(Integer id);



}
