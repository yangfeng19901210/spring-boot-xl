package com.yy.study.controller;

import cn.dsk.service.HelloService;
import com.yy.study.domain.entity.User;
import com.yy.study.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*********************************************************
 ** 测试controller
 ** <br><br>
 ** @ClassName: TestController
 ** @author: yangfeng
 ** @date: 2025/5/27 14:14
 ** @version: 1.0.0
 *********************************************************/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Resource
    private TestService testService;
    @Resource
    private HelloService helloService;
    // 测试方法
    @RequestMapping("/hello")
    public String hello() {
        System.out.println(Thread.currentThread().getId());
        testService.sayHello();
        return "Hello, World!";
    }
    @PostMapping("/testAssert")
    public String testAssert(String name,Integer age) {
        User user = null;
//        Assert.notNull(user,"用户不存在");
//        AssertTool.notNull(user, "用户不存在");
//        throw new BusinessException("用户id不可为空");
        return helloService.sayHello(name);
    }
}
