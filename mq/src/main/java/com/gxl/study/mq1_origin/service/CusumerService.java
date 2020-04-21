package com.gxl.study.mq1_origin.service;

import com.gxl.study.mq1_origin.model.MyConsumer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author gouxi
 * @description
 * @since 2020/4/14
 */
@Service
public class CusumerService {

    public Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.107.144.99");
        connectionFactory.setPort(5672);
        //虚拟主机
        connectionFactory.setVirtualHost("/mq1_origin");
        //用户名密码
        connectionFactory.setUsername("test");
        connectionFactory.setPassword("123");
        return connectionFactory.newConnection();
    }

    public void getMessage(Connection connection){
        try {
            //1. 获取channel
            Channel channel = connection.createChannel();

            String exchangeName = "test_consumer_exchange";
            String queueName = "hello";
            String routingKey = "consumer.#";

            //2. 声明一个exchange
            channel.exchangeDeclare(exchangeName,"topic",true,false,null);

            //3. 声明一个正常的队列
            Map<String,Object> arguments = new HashMap<>();
            arguments.put("x-dead-letter-exchange","dlx.exchange");
            channel.queueDeclare(queueName,true,false,false,arguments);
            //4. 绑定
            channel.queueBind(queueName,exchangeName,routingKey);


            /* 特殊的声明死信队列  start*/
            String deadExchangeName = "dead_exchange";
            String deadQueueName = "deadQueue";
            String deadRoutingKey = "dead.#";
            channel.exchangeDeclare(deadExchangeName,"topic",true,false,null);
            channel.queueDeclare(deadQueueName,true,false,false,null);
            channel.queueBind(deadQueueName,deadExchangeName,deadRoutingKey);
            /* 特殊的声明死信队列  end*/

            //6.接受消息:第二个参数代表是否自动签收消息ack。
            //String message = channel.basicConsume(queueName,true, new MyConsumer(channel));
            //设置消息限流
            channel.basicQos(0,3,false);
            //不自动签收消息，必须确认后才接受
            String message = channel.basicConsume(queueName,false, new MyConsumer(channel));
            System.out.println("message: "+message);

//            try {
//                channel.close();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
