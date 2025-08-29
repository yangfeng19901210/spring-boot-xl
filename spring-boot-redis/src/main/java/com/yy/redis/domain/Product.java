package com.yy.redis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

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
    private Date createdAt;

    /**
     * 最后更新时间
     */
    private Date updatedAt;
}