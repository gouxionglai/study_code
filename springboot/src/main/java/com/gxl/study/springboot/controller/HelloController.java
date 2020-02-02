package com.gxl.study.springboot.controller;

import com.gxl.starter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author gouxi
 * @description
 * @since 2020/1/23
 */

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;

    @RequestMapping("/")
    public String index(){
        System.out.println("redirect to login.html !!!");
        return "login";
    }

    @RequestMapping("/hello/success")
    public String success(Map<String,Object> map){
        map.put("hello","你好");
        map.put("users", Arrays.asList("张三","里斯","王五"));
        map.put("currentDate",new Date());
        //thymeleaf语法：自动配置前后缀，跳转到/template/success.html页面
        return "success";
    }

    @ResponseBody
    @RequestMapping("/hello/testDate")
    public Object testDate(Date cdate){
        Map map = new HashMap();
        map.put("date",cdate);
        map.put("msg","转换成功了");
        return map;
    }

    //测试自定义的start是否注入进去
    @GetMapping("/getHello")
    @ResponseBody
    public String getHello(){
        String result = helloService.sayHello("hi");
        return result;
    }
}
