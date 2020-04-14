package com.gxl.study.mq.model;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author gouxi
 * @description
 * @since 2020/4/14
 */
public class MyConsumer extends DefaultConsumer {

    private Channel channel;
    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("------consume message-----");
        System.out.println("consumerTag:"+consumerTag);
        System.out.println("envelope:"+envelope);
        System.out.println("properties:"+properties);
        System.out.println("body:"+new String(body));
        //super.handleDelivery(consumerTag, envelope, properties, body);

        if(properties !=null && properties.getMessageId() !=null){
            //主动应答正确，批量签收true
            channel.basicAck(envelope.getDeliveryTag(),false);
        }else{
            //应答错误
            channel.basicNack(envelope.getDeliveryTag(),false,false);
        }

        try {
            //为了看到主动签收的效果，睡眠3s
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
