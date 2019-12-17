package com.gxl.study.javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weilai
 * @description
 * @since 2019/12/17
 */
@Controller
@ResponseBody
public class HelloWorldController {
    @RequestMapping("hello")
    public String hello(){
        return "hello  world!!!!!";
    }
}
