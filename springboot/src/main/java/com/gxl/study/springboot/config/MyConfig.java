package com.gxl.study.springboot.config;

import com.gxl.study.springboot.service.HelloService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gouxi
 * @description
 * @since 2020/1/22
 */
//标注是配置类
@Configuration
public class MyConfig {

    //只会初始化一次
    //注意方法名： 容器中组件id就是方法名
    @Bean
    public HelloService2 helloService2(){
        System.out.println("初始化helloService2");
        return new HelloService2();
    }
}
