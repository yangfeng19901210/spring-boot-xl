package com.yy.redis.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品信息表
 * @TableName product
 */
@TableName(value ="product")
@Data
public class Product {
    /**
     * 产品唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
     * 产品详细描述
     */
    private String description;

    /**
     * 是否可用（1:可用, 0:不可用）
     */
    private Integer isAvailable;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 最后更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}