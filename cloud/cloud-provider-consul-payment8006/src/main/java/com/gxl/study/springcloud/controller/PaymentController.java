package com.gxl.study.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author gouxi
 * @description
 * @since 2020/7/21
 */
@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/consul")
    public String payment(){
        return "spring cloud with consul"+ port + UUID.randomUUID();
    }
}
