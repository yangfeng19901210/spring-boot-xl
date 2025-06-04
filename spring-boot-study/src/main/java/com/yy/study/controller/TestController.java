package com.yy.study.controller;

import cn.dsk.service.HelloService;
import com.yy.study.domain.entity.User;
import com.yy.study.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
@Validated
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
    public User test(@Pattern(regexp = ".{3,20}", message = "用户名长度需为3-20字符") String name,
                             @NotNull(message = "年龄不可为空") Integer age) {
        User user = new User(name);
//        Assert.notNull(user,"用户不存在");
//        AssertTool.notNull(user, "用户不存在");
//        throw new BusinessException("用户id不可为空");
        return user;
    }
}
