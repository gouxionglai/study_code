package com.gxl.study.springboot.controller;

import com.gxl.study.springboot.service.SomeAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gouxi
 * @description
 * @since 2020/1/21
 */
@RequestMapping("/demo")
@Controller
public class DemoController {
    @Autowired
    private SomeAsyncService asyncService;

    private Logger logger = LoggerFactory.getLogger(DemoController.class);


    @ResponseBody
    @GetMapping("/test")
    public Object getTest(){
        logger.info("====================/test");
        Map<String,Object> result = new HashMap<>();
        result.put("code",1);
        result.put("msg","this is a hello world springboot project...");
        return result;
    }

    @ResponseBody
    @GetMapping("/async")
    public Object testAsync(){
        logger.info("====================test");
        //不需要等待
        asyncService.doSomethingAsync();
        //需要等待
        asyncService.doSomething();
        return "success";
    }
}
