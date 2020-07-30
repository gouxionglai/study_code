package com.gxl.study.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.gxl.study.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
@Service
public class PaymentServiceImpl implements PaymentService {


    @Override
    public String ok(Integer i) {
        return Thread.currentThread().getName()+":"+i+"正常访问O(∩_∩)O";
    }

    @Override
    @HystrixCommand(fallbackMethod = "timeOut_TimeoutFallbackHandler", commandProperties = {
            //触发条件：超时>=value
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String timeOut(Integer i) {
        //程序报错
        //int a = 10/0;
        //超时报错
        int time = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName()+":"+i+"超时访问O(∩_∩)O";
    }

    @Override
    @HystrixCommand(fallbackMethod = "circuitBreak_FallbackHandler", commandProperties = {
            //触发条件：
            // 3个参数放在一起，所表达的意思就是：
            //每当10个请求中，有60%失败时，熔断器就会打开，此时再调用此服务，将会直接返回失败，不再调远程服务。直到20s钟之后，重新检测该触发条件，判断是否把熔断器关闭，或者继续打开。
            //开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            //请求数达到value之后开始执行判断是否需要熔断 10次
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            //休眠时间窗10000ms ，即多少时间之后服务重新可用
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "20000"),
            //错误率达到多少跳闸  60%
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String circuitBreak(Integer id) {
        if(id<0){
            throw  new RuntimeException("****id 不能为负数");
        }
        String serialNumber = UUID.randomUUID().toString();
        return  Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String circuitBreak_FallbackHandler(@PathVariable("id") Integer id){
        return "id 不能为负数,请稍后再试， o(╥﹏╥)o id: " + id;
    }

    /**
     * fallbackMethod 服务降级触发的调用方法
     * @return
     */
    public String timeOut_TimeoutFallbackHandler(Integer i){
        return Thread.currentThread().getName()+":"+i+"超时访问/(ㄒoㄒ)/~~";
    }
}
