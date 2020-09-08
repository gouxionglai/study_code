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
 * @since 2020/8/31
 */
@Component
@EnableBinding({Sink.class})
public class ReceiveMessageController {
    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT)
    public void receiveMessage(Message<String> message){
        System.out.println("消费者2号......-->"+message.getPayload()+"\t port:"+port);
    }
}
