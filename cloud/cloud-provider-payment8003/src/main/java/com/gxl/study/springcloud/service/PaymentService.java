package com.gxl.study.springcloud.service;


import com.gxl.study.springcloud.entity.Payment;

/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getByID(Long id);
}
