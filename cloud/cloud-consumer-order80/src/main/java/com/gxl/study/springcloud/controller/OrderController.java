package com.gxl.study.springcloud.controller;

import com.gxl.study.springcloud.entity.CommonResult;
import com.gxl.study.springcloud.entity.Payment;
import com.gxl.study.springcloud.rule.ILoadBalanceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
@RequestMapping("/order")
@RestController
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private ILoadBalanceImpl iLoadBalance;

    private static final String PAYMENT_URL = "http://cloud-payment-service/api";
    @GetMapping("/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getByID(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
    }

    @GetMapping("/payment/lb")
    public String getByIDInRule(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if(instances == null || instances.size() <= 0) {
            return "服务集合为空";
        }
        ServiceInstance instance = iLoadBalance.instance(instances);
        if(instance ==null){
            return "筛选出的服务为空";
        }
        URI uri = instance.getUri();
        System.out.println("uri:"+uri);
        //注意这里有个坑：因为已经指定了url了再用@LoadBalance注解会报错 说找不到instance
        return restTemplate.getForObject(uri+"/api/payment/lb",String.class);
        //return restTemplate.getForObject("http://localhost:8002"+"/api/payment/lb",String.class);
    }

}
