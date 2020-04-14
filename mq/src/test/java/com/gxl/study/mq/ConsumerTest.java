package com.gxl.study.mq;

import com.gxl.study.mq.service.CusumerService;
import com.gxl.study.mq.service.ProviderService;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//@SpringBootTest
class ConsumerTest {


//    @Autowired
    private CusumerService cusumerService = new CusumerService();

    @Test
    void testConsumer(){
        try {
            Connection connection = cusumerService.getConnection();
            cusumerService.getMessage(connection);
            //cusumerService.closeConnection(connection);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
