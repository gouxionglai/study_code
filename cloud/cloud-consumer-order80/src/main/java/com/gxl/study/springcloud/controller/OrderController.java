package com.gxl.study.springcloud.controller;

import com.gxl.study.springcloud.entity.CommonResult;
import com.gxl.study.springcloud.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
@RequestMapping("/order")
@RestController
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://localhost:8001/api";
    @GetMapping("/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getByID(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
    }

}
