package com.gxl.study.mq;

import com.gxl.study.mq.service.CusumerService;
import com.gxl.study.mq.service.ProviderService;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class ProviderTest {

    @Autowired
    private ProviderService providerService;


    @Test
    void contextLoads() {
    }

    @Test
    void testProvider(){
        try {
            Connection connection = providerService.getConnection();
            providerService.sendMessage(connection);
            providerService.closeConnection(connection);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
