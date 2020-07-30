package com.gxl.study.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author gouxi
 * @description
 * @since 2020/7/21
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String url = "http://consul-provider-payment";
    @RequestMapping("/consul")
    public String order(){
       return restTemplate.getForObject(url+"/payment/consul",String.class);
    }
}
