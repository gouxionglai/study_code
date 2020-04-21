package com.gxl.study.mq4_springcloud.service.impl;

import com.gxl.study.mq4_springcloud.service.InputBarista;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author gouxi
 * @description
 * @since 2020/4/17
 */
@EnableBinding(InputBarista.class)
@Service
public class Consumer {

    @StreamListener(InputBarista.INPUT_CHANNEL)
    public void getMessage(Message message) throws Exception{
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("-----------------barista接受消息完成------------------");
        //手动响应
        assert channel != null;
        assert deliveryTag != null;
        //false代表一个个签收而不是批量签收
        channel.basicAck(deliveryTag, false);
    }
}
