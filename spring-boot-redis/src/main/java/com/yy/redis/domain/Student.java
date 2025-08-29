package com.yy.redis.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 学生实体类
 * @ClassName Student
 * @Author yangfeng
 * @Date 2025/8/28 10:55
 * @Version 1.0
 */
@Data
public class Student {

    private String name;

    private Integer age;

    private LocalDateTime birthday;
}