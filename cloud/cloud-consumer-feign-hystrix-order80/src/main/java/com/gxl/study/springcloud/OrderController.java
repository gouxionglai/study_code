package com.gxl.study.springcloud;

import com.gxl.study.springcloud.entity.CommonResult;
import com.gxl.study.springcloud.entity.Payment;
import com.gxl.study.springcloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gouxi
 * @description
 * @since 2020/7/24
 */
//默认的降级方法，注意需要配合@HystrixCommand使用才有效果
@DefaultProperties(defaultFallback="global_fallBackHandler")
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/ok/{id}")
    public String ok(@PathVariable("id") Integer id){
        return orderService.ok(id);
    }

    @GetMapping("/timeOut/{id}")
//    @HystrixCommand(fallbackMethod = "timeOut_fallBackHandler", commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000")
//    })
    @HystrixCommand(fallbackMethod = "timeOut_fallBackHandler")
    public String timeOut(@PathVariable("id") Integer id){
        //直接报错
        System.out.println("=========start");
        //int a= 10/0;    //不会走到后面直接走降级方法
        System.out.println("=========end");
        return orderService.timeOut(id);
    }

    /**
     * 注意callBack的方法必须和原调用方法一致，参数这些
     * @return
     */
    public String timeOut_fallBackHandler(Integer id){
        return Thread.currentThread().getName()+":调用对方方法失败/(ㄒoㄒ)/~~";
    }

    /**
     * 全局降级方法
     * @return
     */
    public String global_fallBackHandler(){
        return Thread.currentThread().getName()+":全局降级方法/(ㄒoㄒ)/~~";
    }

    @GetMapping("test")
    @HystrixCommand
    public String test(){
        //int a = 10/0;
        return "test ok";
    }
}
