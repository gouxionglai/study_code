package com.gxl.study.springboot;

import com.gxl.study.springboot.utils.ApplicationContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//开启缓存注解，使用cache才有效
@EnableCaching
//MapperScan扫描的是java文件
//yml里面配置的是xml文件地址
@MapperScan("com.gxl.study.springboot.mapper")
@EnableWebMvc
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
        System.out.println("server start!!!");
        Object dataSource = ApplicationContextUtil.getBean("dataSource");
        System.out.println(dataSource);
        System.out.println();
    }

}
