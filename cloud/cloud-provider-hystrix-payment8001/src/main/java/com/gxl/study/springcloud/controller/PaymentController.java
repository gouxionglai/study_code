package com.gxl.study.springcloud.controller;

import com.gxl.study.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
@RequestMapping("/payment/hystrix")
@RestController
public class PaymentController {

    private Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;


    @GetMapping("/ok/{id}")
    public String ok(@PathVariable("id") Integer id){
        String result =  paymentService.ok(id);
        logger.info("result:==="+result);
        return result;
    }

    @GetMapping("/timeOut/{id}")
    public String timeOut(@PathVariable("id") Integer id){
        String result = paymentService.timeOut(id);
        logger.info("result:==="+result);
        return result;
    }

    @GetMapping("/circuit/{id}")
    public String circuit(@PathVariable("id") Integer id){
        return paymentService.circuitBreak(id);
    }


}
