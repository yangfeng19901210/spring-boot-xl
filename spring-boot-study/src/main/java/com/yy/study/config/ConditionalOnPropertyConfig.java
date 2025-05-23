package com.yy.study.config;

import com.yy.study.domain.entity.Dog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*********************************************************
 ** 根据配置文件动态注册Bean配置
 ** <br><br>
 ** @ClassName: ConditionalOnPropertyConfig
 ** @author: yangfeng
 ** @date: 2025/5/23 14:51
 ** @version: 1.0.0
 *********************************************************/
@Configuration
//@ConditionalOnProperty("favoutite")
@ConditionalOnProperty(prefix = "favourite", name = "dog", havingValue = "fish")
public class ConditionalOnPropertyConfig {
    @Bean("dog")
    public Dog dog(){
        return new Dog("小天",1,"棕色");
    }
}
