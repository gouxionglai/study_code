package com.gxl.study.mq2_amqp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;


@SpringBootTest
class AmqpTest {


    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private String exchangeName = "amqpExchange";
    private String queueName = "amqpQueue";
    private String routingKey = "amqpRoutingKey";

    @Test
    void testRabbitAdmin(){
//        String exchangeName = "amqpExchange";
//        String queueName = "amqpQueue";
//        String routingKey = "amqpRoutingKey";
        //1.声明exchange
        //new DirectExchange 默认是durable持久的
        rabbitAdmin.declareExchange(new DirectExchange(exchangeName,false,false));
        //rabbitAdmin.declareExchange(new TopicExchange(exchangeName,false,false));
        //2.声明queue
        rabbitAdmin.declareQueue(new Queue(queueName,false));
        //3.声明binding
        rabbitAdmin.declareBinding(new Binding(queueName,Binding.DestinationType.QUEUE,exchangeName,routingKey,null));

        //一起声明，同上面的123
        rabbitAdmin.declareBinding(BindingBuilder
                .bind(new Queue(queueName,false))
                .to(new DirectExchange(exchangeName,false,false))
                .with(routingKey)
        );

    }

    @Test
    void testTlearQueue(){
//        String queueName = "amqpQueue";
        //清空队列数据
        rabbitAdmin.purgeQueue(queueName,false);
    }

    @Test
    void testRabbitTemplate(){
//        String exchangeName = "amqpExchange";
//        String routingKey = "amqpRoutingKey";

        MessageProperties properties = new MessageProperties();
        properties.setHeader("encoding","utf-8");
        properties.getHeaders().put("desc","信息描述");
        properties.setAppId(UUID.randomUUID().toString());
        Message message = new Message("hello rabbitTemplate1".getBytes(),properties);
        rabbitTemplate.send(exchangeName,routingKey,message);

        //convertAndSend其底层还是调用的send,只不过message可以是正常的字符串 亦可以是Message对象
        rabbitTemplate.convertAndSend(exchangeName, routingKey, "hello rabbitTemplate2");
        //也可以加消息后置处理
        //rabbitTemplate.convertAndSend("amqpExchange", "amqpRoutingKey", message, new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                System.out.println("----添加额外的设置----");
//                message.getMessageProperties().getHeaders().put("desc","重置描述");
//                message.getMessageProperties().getHeaders().put("attr","额外的属性");
//                return message;
//            }
//        });
    }

    @Test
    void getMessages(){
        /*
        * receiveAndConvert:相比receive多了fromMessage(response)，只拿出body部分。
        * 1.如果他在前面，则拿到的是数组，
        * 2.如果有receive在前面，则正常拿到的是string (根据前面的编码来)
        *
        * */

//        String queueName = "amqpQueue";
        //得到原生的Message对象，由body和messageProperties构成
        //Message重写了toString方法
        Message message2 = rabbitTemplate.receive(queueName);
        System.out.println(message2);


        //默认不等待，没有消息就直接返回
        //其实是在receive的基础上增加了this.getRequiredMessageConverter().fromMessage(response)
        //fromMessage没有被重写，所以默认调用的是SimpleMessageConverter,直接返回原生的body
        Object message1 = rabbitTemplate.receiveAndConvert(queueName);
        //param2:timeoutMillis 即等待多少毫秒之后无消息才返回null
        //Object message1 = rabbitTemplate.receiveAndConvert(queueName, 1000);
        if(message1 instanceof byte[]){
            byte[] array1 = (byte[]) message1;
            System.out.println(new String(array1));
        }

    }

}
