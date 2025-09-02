package com.yy.redis.vo.in;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 新增商品请求参数
 * @ClassName AddProductInVO
 * @Author yangfeng
 * @Date 2025/9/2 9:15
 * @Version 1.0
 */
@Data
public class AddProductInVO {
    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品类别
     */
    private String category;

    /**
     * 产品价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer stockQuantity;
    /**
     * 商品创建时间，验证反序列化是否正常
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}