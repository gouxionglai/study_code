package com.gxl.study.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author gouxi
 * @description
 * @since 2020/9/8
 */
@RestController
public class OrderController {


    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-use-service}")
    private String serverURL= "";

    @GetMapping("/consumer/payment/{id}")
    public String getPayment(@PathVariable Integer id){
        return restTemplate.getForObject(serverURL+"/payment/"+id, String.class);
    }
}
