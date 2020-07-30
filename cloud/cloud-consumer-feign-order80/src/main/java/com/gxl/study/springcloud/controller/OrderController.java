package com.gxl.study.springcloud.controller;

import com.gxl.study.springcloud.entity.CommonResult;
import com.gxl.study.springcloud.entity.Payment;
import com.gxl.study.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gouxi
 * @description
 * @since 2020/7/24
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getByID(@PathVariable("id") Long id){
        return orderService.getByID(id);
    }

    @GetMapping("/getPort")
        public String getPort(){
        return orderService.getPort();
    }

    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}
