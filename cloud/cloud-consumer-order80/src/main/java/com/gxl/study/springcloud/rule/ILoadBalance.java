package com.gxl.study.springcloud.rule;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author gouxi
 * @description 自定义LoadBalance
 * @since 2020/7/23
 */
public interface ILoadBalance {
    /**
     * 收集现有服务实例，根据某个算法返回其中一个可用实例
     * @param serviceInstances
     * @return
     */
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
