package com.gxl.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
//自定义负载均衡
//@RibbonClient(name = "cloud-payment-service",configuration = MySelfRule.class)
@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Order80Application {
    public static void main(String[] args) {
        SpringApplication.run(Order80Application.class,args);
    }
}
