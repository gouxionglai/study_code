package com.gxl.study.springcloud.service;

import com.gxl.study.springcloud.entity.CommonResult;
import com.gxl.study.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gouxi
 * @description
 * @since 2020/7/24
 */
//声明使用openFeign，并且指定具体的微服务名称  需要配合@EnableFeignClients
@FeignClient("cloud-payment-service")
@Service
public interface OrderService {
    //这样的话 就直接调用对应服务的该方法
    //但是这里的mapping地址也要像之前controller里面restTemplate调用一样，完整的url
    //比如有context-path: /api 的都要补全
    @GetMapping("/api/payment/get/{id}")
    CommonResult<Payment> getByID(@PathVariable("id") Long id);

    @GetMapping("/api/payment/getPort")
    String getPort();
}
