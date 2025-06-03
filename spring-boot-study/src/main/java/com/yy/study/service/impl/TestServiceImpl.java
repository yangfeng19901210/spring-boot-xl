package com.yy.study.service.impl;

import com.yy.study.service.TestService;
import org.springframework.stereotype.Service;

/*********************************************************
 **
 ** <br><br>
 ** @ClassName: TestServiceImpl
 ** @author: yangfeng
 ** @date: 2025/5/28 8:51
 ** @version: 1.0.0
 *********************************************************/
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void sayHello() {
        System.out.println(Thread.currentThread().getId());
        System.out.println("Hello from TestServiceImpl!");
    }
}
