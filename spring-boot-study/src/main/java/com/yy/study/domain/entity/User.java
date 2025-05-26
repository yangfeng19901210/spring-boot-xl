package com.yy.study.domain.entity;

import lombok.Data;

/*********************************************************
 **
 ** <br><br>
 ** @ClassName: User
 ** @author: yangfeng
 ** @date: 2025/5/26 17:44
 ** @version: 1.0.0
 *********************************************************/
@Data
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }
}
