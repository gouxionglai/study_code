package com.gxl.study.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author gouxi
 * @description
 * @since 2020/2/5
 */
@SpringBootTest
public class RabbitMQTests {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    void testConnection(){
        //原声的发送消息，指定exchange和routeKey， 还有消息内容需要封装成Message对象
//        rabbitTemplate.send(exchange, routeKey, message);
        //所以提供了更方便的方法
        HashMap<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个rabbitMQ消息");
        map.put("detail", Arrays.asList("hello","-world","-rabbit"));
        rabbitTemplate.convertAndSend("exchange.topic","gxl.news",map);
        System.out.println("==========success!");

        //同样的 获取也提供了两种方式
        //获取到Message对象 自己转换成具体的消息
//        Message receive = rabbitTemplate.receive("gxl.news");
        //获取到指定类型的对象，根据自己配置的messageConverter转换，默认是jdk的序列化方式
        Object o = rabbitTemplate.receiveAndConvert("gxl.news");
        System.out.println(o);
    }

}
