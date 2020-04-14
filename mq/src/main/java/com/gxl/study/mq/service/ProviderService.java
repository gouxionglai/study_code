package com.gxl.study.mq.service;

import com.rabbitmq.client.AMQP;
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
 * @since 2020/4/10
 */
@Service
public class ProviderService {

    private Connection connection;
    //
    public Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //ip + 端口
        connectionFactory.setHost("47.107.144.99");
        connectionFactory.setPort(5672);
        //虚拟主机
        connectionFactory.setVirtualHost("/mq");
        //用户名密码
        connectionFactory.setUsername("test");
        connectionFactory.setPassword("123");

        //获取连接对象
        return connectionFactory.newConnection();
    }

    public void sendMessage(Connection connection){
        if(connection ==null){
            return;
        }
        //获取通道
        Channel channel = null;
        try {
            channel = connection.createChannel();
            //通道绑定对应消息队列
            //参数1：exchange
            //参数2：routing key
            //参数3：message的properties
            //参数4：message的body 需要格式为bytes[]
            String exchangeName = "test_consumer_exchange";
            String routingKey = "consumer.save";
            String message = "hello rabbit";

            Map<String,Object> headers = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                //设置properties
                headers.put("num",i);
                AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                        .deliveryMode(2)    // 2代表持久化消息，默认是2
                        .contentEncoding("UTF-8")
                        .headers(headers)
                        .build();
                channel.basicPublish(exchangeName,routingKey,properties,(message+i).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(channel!=null){
                try {
                    channel.close();
                } catch (IOException | TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void closeConnection(Connection connection){
        if(connection !=null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
