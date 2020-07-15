package com.gxl.study.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
@EnableEurekaClient
@MapperScan(basePackages = "com.gxl.study.springcloud.mapper")
@SpringBootApplication
public class PaymentApplication8003 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8003.class, args);
    }
}
