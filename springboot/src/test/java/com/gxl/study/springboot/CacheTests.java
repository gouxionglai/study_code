package com.gxl.study.springboot;

import com.gxl.study.springboot.mapper.EmployeeMapper;
import com.gxl.study.springboot.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author gouxi
 * @description
 * @since 2020/2/5
 */
@SpringBootTest
public class CacheTests {
    //k v 都是Object
    @Autowired
    RedisTemplate redisTemplate;

    //k v 都是String
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    EmployeeMapper employeeMapper;


    /**
     * redis常见五大类型
     * String, List列表, Set集合, Hash散列, Zset有序集合
     */
    //验证redis缓存
    @Test
    public void testRedisConnection(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.append("test","world");
        String test = stringStringValueOperations.get("test");
        System.out.println(test);
    }

    @Test
    public void testRedisEmployee(){
        ValueOperations<String, Employee> valueOperations = redisTemplate.opsForValue();
        Employee employee = employeeMapper.getById(2);
        if(employee !=null){
            System.out.println(employee);
            //需要实现序列化接口才能存储。因为默认使用的jdk序列化规则
            valueOperations.set("emp1",employee);
            //相当于命令setnx key value,防止数据覆盖
            valueOperations.setIfAbsent("emp1",new Employee());
        }
    }
}
