package com.gxl.study.mq1_origin;

import com.gxl.study.mq1_origin.service.CusumerService;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;

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
