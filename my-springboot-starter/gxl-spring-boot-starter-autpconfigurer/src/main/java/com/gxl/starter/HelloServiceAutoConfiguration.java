package com.gxl.starter;

import com.gxl.starter.service.HelloProperties;
import com.gxl.starter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gouxi
 * @description
 * @since 2020/1/31
 */
@Configuration
@ConditionalOnWebApplication    //WEB应用才生效
@EnableConfigurationProperties(HelloProperties.class)   //让属性文件生效
public class HelloServiceAutoConfiguration {

    @Autowired
    HelloProperties helloProperties;

    @Bean
    public HelloService helloService(){
        HelloService helloService = new HelloService();
        helloService.setHelloProperties(helloProperties);
        return helloService;
    }
}

