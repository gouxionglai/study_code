package com.gxl.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gouxi
 * @description
 * @since 2020/9/8
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication9002.class, args);
    }
}
