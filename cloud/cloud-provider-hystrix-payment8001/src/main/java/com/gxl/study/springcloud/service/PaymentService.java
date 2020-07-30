package com.gxl.study.springcloud.service;


/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
public interface PaymentService {
    String ok(Integer i);

    String timeOut(Integer i);

    String circuitBreak(Integer id);
}
