package com.gxl.study.redis;

import com.gxl.study.redis.mapper.UserMapper;
import com.gxl.study.redis.model.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Iterator;
import java.util.Set;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        System.out.println(userMapper);
    }


    @Test
    void testMybatis(){
        User user = new User();
        user.setUno("00001");
        user.setUname("测试1");
        user.setUpass("123456");

        int result = userMapper.insertSelective(user);
        System.out.println(result);
    }

    @Test
    void testRedis(){
//        Set<String> keys1 = stringRedisTemplate.keys("*");
//        Iterator<String> iterator = keys1.iterator();
//        while (iterator.hasNext()){
//            String next = iterator.next();
//            System.out.println(next);
//        }
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent("hello", "hello world");
        System.out.println(aBoolean);
        String hello = stringRedisTemplate.opsForValue().get("hello");
        System.out.println(hello);
//        Boolean hello = redisTemplate.delete("hello");
//        System.out.println(hello);
//        Set<String> keys = redisTemplate.keys("t_*");
//        assert keys != null;
//        for(String key : keys){
//            System.out.println(key);
//        }
    }

}
