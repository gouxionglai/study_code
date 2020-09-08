package com.gxl.study.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author gouxi
 * @description
 * @since 2020/9/8
 */
@Configuration
public class ApplicationContextConfig {

    //注入restTemplate
    @Bean
    @LoadBalanced   //负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
