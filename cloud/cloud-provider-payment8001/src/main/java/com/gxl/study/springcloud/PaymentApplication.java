package com.gxl.study.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
@MapperScan(basePackages = "com.gxl.study.springcloud.mapper")
@SpringBootApplication
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
