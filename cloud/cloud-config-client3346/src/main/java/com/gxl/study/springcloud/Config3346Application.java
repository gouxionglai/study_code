package com.gxl.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author gouxi
 * @description  完全copy3345
 * @since 2020/8/15
 */
@EnableEurekaClient
@SpringBootApplication
public class Config3346Application {
    public static void main(String[] args) {
        SpringApplication.run(Config3346Application.class, args);
    }
}
