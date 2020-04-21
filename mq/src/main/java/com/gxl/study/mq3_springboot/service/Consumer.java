package com.gxl.study.mq3_springboot.service;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.nio.channels.Channel;

/**
 * @author gouxi
 * @description
 * @since 2020/4/17
 */
@Service
public class Consumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",durable = "true"),
            exchange = @Exchange(value = "exchange-1", durable = "true",type = "topic"),
            key = "springboot.#"
        )
    )
    @RabbitHandler  //和RabbitListener绑定使用
    public void onMessage(Message message, Channel channel){
        System.out.println("消费端收到消息："+message.getPayload());
    }
}
