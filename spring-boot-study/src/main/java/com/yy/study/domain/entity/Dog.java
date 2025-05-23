package com.yy.study.domain.entity;

/*********************************************************
 **
 ** <br><br>
 ** @ClassName: Dog
 ** @author: yangfeng
 ** @date: 2025/5/23 15:01
 ** @version: 1.0.0
 *********************************************************/
public class Dog {
    private String name;
    private Integer age;
    private String color;

    public Dog(String name, Integer age, String color) {
        System.out.println("初始化有参数dog");
        this.name = name;
        this.age = age;
        this.color = color;
    }
    public Dog() {
        System.out.println("dog正在初始化");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
