package com.gxl.study.springcloud.service;

import com.gxl.study.springcloud.service.impl.OrderServiceHystrixImpl;
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
@FeignClient(value = "cloud-provider-hystrix-payment",fallback = OrderServiceHystrixImpl.class)
@Service
public interface OrderService {

    @GetMapping("/api/payment/hystrix/ok/{id}")
    String ok(@PathVariable("id") Integer id);

    @GetMapping("/api/payment/hystrix/timeOut/{id}")
    String timeOut(@PathVariable("id") Integer id);
}
