package com.gxl.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gouxi
 * @description
 * @since 2020/7/21
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulOrder80Application {
    public static void main(String[] args) {
        SpringApplication.run(ConsulOrder80Application.class, args);
    }
}
