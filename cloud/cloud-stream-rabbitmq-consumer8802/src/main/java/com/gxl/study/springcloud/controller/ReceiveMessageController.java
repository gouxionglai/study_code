package com.gxl.study.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author gouxi
 * @description
 * @since 2020/8/30
 */
@Component
@EnableBinding({Sink.class})    //接受消息，并不是主动接受，而是被动，所以不是RestController
public class ReceiveMessageController {

    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT) //监听
    public void receiveMessage(Message<String> message){
        System.out.println("消费者1号......-->"+message.getPayload()+"\t port:"+port);
    }
}
