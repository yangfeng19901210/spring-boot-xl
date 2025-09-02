package com.yy.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.redis.domain.Product;
import com.yy.redis.service.ProductService;
import com.yy.redis.mapper.ProductMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
* @author yangfeng
* @description 针对表【product(产品信息表)】的数据库操作Service实现
* @createDate 2025-08-29 11:57:14
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

    @Cacheable(value = "productCache", key = "'selectById:' + #id", unless = "#result == null")
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
//    @CachePut(value = "productCache", key = "'selectById:' + #product.id")
    /**
     *1.@CachePut: 将当前方法的返回值（通常就是更新后的 product 对象）重新放入**名为 productCache 的缓存中，并且使用 #product.id 作为缓存的键
     *2.@CacheEvict: 从同一个缓存区域 (productCache) 中移除**某个条目。因为 allEntries 设置为 false（这也是默认值），
     * 所以它不会清空整个 productCache 缓存，而是依赖于 key 来删除特定条目。不过，在你提供的代码片段中，
     * @CacheEvict 的 key 属性似乎没有明确指定，这可能会导致意料之外的行为
     * 3.总结
     * @CacheEvict（在正确配置 key 的前提下）尝试移除 productCache 中可能存在的、与该产品相关的其他陈旧缓存数据（如产品列表缓存），但不会清空整个 productCache
     */
    @Caching(
            put = {@CachePut(value = "productCache", key = "'selectById:' + #product.id")},
            evict = {@CacheEvict(value = "productCache",key = "selectList", allEntries = false)}
    )
    @Override
    public Product updateProduct(Product product) {
        updateById(product);
        return product;
    }

    @CacheEvict(value = "productCache", key = "#id")
    public boolean deleteProduct(Integer id) {
        return removeById(id);
    }
}




