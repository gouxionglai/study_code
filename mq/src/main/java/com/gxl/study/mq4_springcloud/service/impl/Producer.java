package com.gxl.study.mq4_springcloud.service.impl;

import com.gxl.study.mq4_springcloud.service.OutputBarista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author gouxi
 * @description
 * @since 2020/4/17
 */
@EnableBinding(OutputBarista.class)
@Service
public class Producer {
    @Autowired
    private OutputBarista barista;

    //发送消息
    public String sendMessage(Object message, Map<String, Object> properties) throws Exception{

        try{
            MessageHeaders headers = new MessageHeaders(properties);
            Message msg = MessageBuilder.createMessage(message,headers);
            boolean sendStatus = barista.logoutput().send(msg);
            System.out.println("-------barista发送消息完成："+sendStatus+"-------");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
