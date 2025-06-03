package com.yy.common.annotation;

import com.yy.common.exception.GlobalExceptionHandler;
import com.yy.tool.SpringTool;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/*********************************************************
 ** 通用的配置类
 ** <br><br>
 ** @ClassName: CommonConfig
 ** @author: yangfeng
 ** @date: 2025/6/3 10:40
 ** @version: 1.0.0
 *********************************************************/
@Target(value = {ElementType.TYPE})  //作用与类
@Retention(RetentionPolicy.RUNTIME)  //运行时注解
@Documented
@Import({GlobalExceptionHandler.class,
        SpringTool.class
})
public @interface CommonConfig {
}
