package com.yy.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.redis.domain.Product;
import com.yy.redis.service.ProductService;
import com.yy.redis.mapper.ProductMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
* @author yangfeng
* @description 针对表【product(产品信息表)】的数据库操作Service实现
* @createDate 2025-08-29 11:57:14
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

    @Cacheable(value = "productCache", key = "'selectById:' + #id")
    @Override
    public Product selectById(Integer id) {
        return getById(id);
    }
    @CachePut(value = "productCache", key = "T(String).valueOf(#root.methodName).concat(':').concat(#product.id)")
    @Override
    public Product addProduct(Product product) {
        save(product);
        return product;
    }
    @CachePut(value = "productCache", key = "'selectById:' + #product.id")
    @Override
    public Product updateProduct(Product product) {
        updateById(product);
        return product;
    }
}




