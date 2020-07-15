package com.gxl.study.springcloud.service.impl;

import com.gxl.study.springcloud.entity.Payment;
import com.gxl.study.springcloud.mapper.PaymentMapper;
import com.gxl.study.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return paymentMapper.insert(payment);
    }

    @Override
    public Payment getByID(Long id) {
        return paymentMapper.selectByPrimaryKey(id);
    }
}
