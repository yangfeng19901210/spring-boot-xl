package com.yy.study.thread.local;

import com.yy.study.domain.entity.User;

public class MyRunnable implements Runnable {

    @Override
    public void run() {

        User user = new User("User-" + Thread.currentThread().getName());
        UserContextHolder.setUser(user);

        // 执行业务逻辑
        System.out.println("当前线程：" + Thread.currentThread().getName() + "，用户：" + UserContextHolder.getUser().getName());

        UserContextHolder.removeUser();
    }
}