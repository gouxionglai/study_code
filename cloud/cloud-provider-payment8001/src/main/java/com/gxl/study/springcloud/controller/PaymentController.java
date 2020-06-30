package com.gxl.study.springcloud.controller;

import com.gxl.study.springcloud.entity.CommonResult;
import com.gxl.study.springcloud.entity.Payment;
import com.gxl.study.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
@RequestMapping("/payment")
@RestController
public class PaymentController {

    private Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        logger.info("插入结果是："+result);
        CommonResult commonResult;
        if(result >0){
            return new CommonResult(200,"create success!!",result);
        }else{
            return new CommonResult(500,"create failed!!",result);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getByID(@PathVariable("id") Long id){

        Payment result = paymentService.getByID(id);
        logger.info("查询的结果是："+result);
        if(result != null){
            return new CommonResult<Payment>(200,"query success!!",result);
        }else{
            return new CommonResult<Payment>(500,"query failed!!",result);
        }
    }


}
