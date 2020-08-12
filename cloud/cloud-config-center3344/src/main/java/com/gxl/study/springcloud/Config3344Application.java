package com.gxl.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author gouxi
 * @description
 * @since 2020/8/12
 */
@EnableConfigServer
@SpringBootApplication
public class Config3344Application {
    public static void main(String[] args) {
        SpringApplication.run(Config3344Application.class, args);
    }
}
