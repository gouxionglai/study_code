package com.gxl.study.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gouxi
 * @description
 * @since 2020/7/21
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        //轮询
//        return new RoundRobinRule();
        //随机
        return new RandomRule();
    }
}
