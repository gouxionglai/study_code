package com.gxl.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//需要扫描的包在application下面 则不用去加ComponentScan
//@ComponentScan("com.gxl.study.mq2_amqp.config")
@SpringBootApplication
public class MqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }

}
