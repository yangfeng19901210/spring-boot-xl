package com.yy.study;

import com.yy.common.annotation.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*********************************************************
 **
 ** <br><br>
 ** @ClassName: StudyApplication
 ** @author: yangfeng
 ** @date: 2025/5/23 14:00
 ** @version: 1.0.0
 *********************************************************/
@SpringBootApplication
@CommonConfig
public class StudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }
}
