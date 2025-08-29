package com.yy.redis;

import com.yy.redis.domain.Student;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * redis基础测试
 * @ClassName RedisBasicTest
 * @Author yangfeng
 * @Date 2025/8/28 10:58
 * @Version 1.0
 */
@SpringBootTest
public class RedisBasicTest {
    @Resource
    RedisTemplate<String, Object> redisTemplate;


    @Test
    public void testSetObj(){
        Student student = new Student();
        student.setName("三");
        student.setAge(18);
        student.setBirthday(LocalDateTime.now());
        redisTemplate.opsForValue().set("stu1",student);
    }

    @Test
    public void testGetObj(){
        Student student1 = (Student)redisTemplate.opsForValue().get("stu1");
        System.out.println(student1);
    }
    @Test
    public void testSetList(){
        Student student1 = new Student();
        student1.setName("三");
        student1.setAge(18);
        student1.setBirthday(LocalDateTime.now());
        Student student2 = new Student();
        student2.setName("李四");
        student2.setAge(19);
        student2.setBirthday(LocalDateTime.now());
        redisTemplate.opsForValue().set("stu2", Arrays.asList(student1,student2));
    }
    @Test
    public void testGetList(){
        List<Student> students = (List<Student>)redisTemplate.opsForValue().get("stu2");
        System.out.println(students);
    }
}