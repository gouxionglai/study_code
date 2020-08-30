package com.gxl.study.springcloud;

import com.gxl.study.springcloud.entity.CommonResult;
import com.gxl.study.springcloud.entity.Payment;
import com.gxl.study.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        logger.info("插入结果是："+result);
        CommonResult commonResult;
        if(result >0){
            return new CommonResult(200,"create success!!port:"+port, result);
        }else{
            return new CommonResult(500,"create failed!!",result);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getByID(@PathVariable("id") Long id){

        Payment result = paymentService.getByID(id);
        logger.info("查询的结果是："+result);
        if(result != null){
            return new CommonResult<Payment>(200,"query success!!port:"+port ,result);
        }else{
            return new CommonResult<Payment>(500,"query failed!!",result);
        }
    }


    @GetMapping("/lb")
    public String getLb(){
        return port;
    }


    /**
     * 超时调用
     * @return
     */
    @GetMapping("/getPort")
    public String getPort(){
        try {
            //Thread.sleep(3000);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
    /**
     * @return 自定义返回服务列表
     */
    @RequestMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String s : services){
            logger.info("服务名："+s);
        }

        //获取指定service名称的实例集群
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances){
            logger.info("实例：id: "+instance.getServiceId()+", host: "+instance.getHost()+"port: "+instance.getPort());
        }
        return this.discoveryClient;
    }

}
