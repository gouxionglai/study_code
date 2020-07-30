package com.gxl.study.springcloud.rule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gouxi
 * @description
 * @since 2020/7/23
 */
@Component
public class ILoadBalanceImpl implements ILoadBalance {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private int getIndex(int count){
        int current;
        int next;
        do{
            current = atomicInteger.get();
            next = current>= 2147483647? 0: (current+1)% count;
        }
        //自旋cas，保证原子性
        while (!atomicInteger.compareAndSet(current,next));
        System.out.println("******next:"+next);
        return next;
    }
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        if(serviceInstances == null || serviceInstances.size() <= 0) {
            return null;
        }
        int index = getIndex(serviceInstances.size());
        return serviceInstances.get(index);
    }
}
