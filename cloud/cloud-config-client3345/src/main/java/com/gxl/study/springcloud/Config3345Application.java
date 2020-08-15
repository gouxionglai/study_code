package com.gxl.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author gouxi
 * @description
 * @since 2020/8/14
 */
@EnableEurekaClient
@SpringBootApplication
public class Config3345Application {
    public static void main(String[] args) {
        SpringApplication.run(Config3345Application.class, args);
    }
}
