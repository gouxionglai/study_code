package com.gxl.study.springcloud.service.impl;

import com.gxl.study.springcloud.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author gouxi
 * @description 降级实现类，达到解耦的效果
 * @since 2020/7/30
 */
@Service
public class OrderServiceHystrixImpl implements OrderService {
    @Override
    public String ok(Integer id) {
        return "OrderServiceHystrixImpl:ok 失败/(ㄒoㄒ)/~~";
    }

    @Override
    public String timeOut(Integer id) {
        return "OrderServiceHystrixImpl:timeOut 失败/(ㄒoㄒ)/~~";
    }
}
