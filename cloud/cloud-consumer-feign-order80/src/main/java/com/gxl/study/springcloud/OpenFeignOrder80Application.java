package com.gxl.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gouxi
 * @description
 * @since 2020/7/24
 */
@EnableFeignClients //启动OpenFeign
@EnableDiscoveryClient  //服务注册
@SpringBootApplication
public class OpenFeignOrder80Application {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignOrder80Application.class,args);
    }
}
