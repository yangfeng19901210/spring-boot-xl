package com.yy.study.thread.local;

public class ThreadLocalExample {
   
    // 创建一个ThreadLocal变量
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
   
        // 在主线程中设置ThreadLocal变量的值
        threadLocal.set("主线程的值");

        // 在主线程中获取ThreadLocal变量的值
        System.out.println("主线程获取的值：" + threadLocal.get());

        // 启动两个子线程
        for (int i = 0; i < 2; i++) {
   
            new Thread(() -> {
   
                // 子线程设置自己的ThreadLocal变量的值
                threadLocal.set(Thread.currentThread().getName() + "的值");

                // 子线程获取自己的ThreadLocal变量的值
                System.out.println(Thread.currentThread().getName() + "获取的值：" + threadLocal.get());

                // 子线程结束后清理ThreadLocal变量
                threadLocal.remove();
            }).start();
        }

        // 主线程结束后清理ThreadLocal变量
        threadLocal.remove();
    }
}
