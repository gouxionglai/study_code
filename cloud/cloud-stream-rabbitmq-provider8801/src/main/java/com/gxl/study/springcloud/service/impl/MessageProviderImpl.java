package com.gxl.study.springcloud.service.impl;

import com.gxl.study.springcloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @author gouxi
 * @description
 * @since 2020/8/30
 */
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {

    //发送消息的管道
    @Autowired
    private MessageChannel output;
    @Override
    public String send() {
        String uuid = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("send success------:"+uuid);
        return uuid;
    }
}
