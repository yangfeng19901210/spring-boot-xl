package com.yy.tool;

import io.gitee.loulan_yxq.owner.core.tool.AssertTool;
import io.gitee.loulan_yxq.owner.core.tool.ObjectTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SpringTool implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    
    /**
     * 重写实现方法
     * @param       
     * @return
     * @exception  
     * @author     :loulan
     * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ObjectTool.isNull(SpringTool.applicationContext)) {
            SpringTool.applicationContext = applicationContext;
        }
    }

    /**
     * 获取spring上下文对象
     * @param
     * @return
     * @exception
     * @author     :loulan
     * */
    public static ApplicationContext getApplicationContext() {
        AssertTool.notNull(applicationContext, "spring上下文对象还没有生成");
        return applicationContext;
    }

    /**
     * 通过name获取Bean.
     * @param
     * @return
     * @exception
     * @author     :loulan
     * */
    public static <T> T getBean(String name) {
        return (T)getApplicationContext().getBean(name);
    }


    /**
     * 通过class获取Bean.
     * @param       
     * @return
     * @exception  
     * @author     :loulan
     * */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     * @param
     * @return
     * @exception
     * @author     :loulan
     * */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
