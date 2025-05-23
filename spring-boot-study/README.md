# 1.springboot之@Conditional注解的条件化注册机制详解

## 1.1@conditionalOnProperty

@ConditionalOnProperty` 是 Spring Boot 中用于 **根据配置文件属性动态控制 Bean 或配置类加载** 的核心条件注解。它通过读取 `application.properties`/`application.yml` 中的属性值，决定是否将特定组件注册到 Spring 容器中，是配置驱动开发的利器

参考连接 https://www.cnblogs.com/xiaomaomao/p/14012795.html

@ConditionalOnProperty 在使用时必须指定value或者name，如果两者都不指定，则启动时会报错

![image-20250523151019962](README.assets/image-20250523151019962.png)

源码

```
package org.springframework.boot.autoconfigure.condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnPropertyCondition.class)
public @interface ConditionalOnProperty {

	String[] value() default {};
	/**
	 * A prefix that should be applied to each property. The prefix automatically ends
	 * with a dot if not specified. A valid prefix is defined by one or more words
	 * separated with dots (e.g. {@code "acme.system.feature"}).
	 * @return the prefix
	 */
	String prefix() default "";

	/**
	 * The name of the properties to test. If a prefix has been defined, it is applied to
	 * compute the full key of each property. For instance if the prefix is
	 * {@code app.config} and one value is {@code my-value}, the full key would be
	 * {@code app.config.my-value}
	 * <p>
	 * Use the dashed notation to specify each property, that is all lower case with a "-"
	 * to separate words (e.g. {@code my-long-property}).
	 * @return the names
	 */
	String[] name() default {};

	/**
	 * The string representation of the expected value for the properties. If not
	 * specified, the property must <strong>not</strong> be equal to {@code false}.
	 * @return the expected value
	 */
	String havingValue() default "";

	/**
	 * Specify if the condition should match if the property is not set. Defaults to
	 * {@code false}.
	 * @return if the condition should match if the property is missing
	 */
	boolean matchIfMissing() default false;

}

```



只配置value属性，如果只有一个value属性，那么value属性可以省略不写

```
@Configuration
@ConditionalOnProperty("favoutite")
public class ConditionalOnPropertyConfig {
    @Bean("dog")
    public Dog dog(){
        return new Dog("小天",1,"棕色");
    }
}
```

prefix、name、havingValue()

prefix不可单独使用，必须和name组合才能代表一个完整的value，也就是prefix+name的组合和value指定的效果是一样的

配置的属性值和配置文件中的属性值不一致，所以注册条件不成立不进行注册

![image-20250523153836433](README.assets/image-20250523153836433.png)

配置文件和注解中配置的条件一致：

![image-20250523153958722](README.assets/image-20250523153958722.png)