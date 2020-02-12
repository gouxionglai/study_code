package com.gxl.study.springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author gouxi
 * @description
 * @since 2020/2/10
 */
@Service
public class SomeAsyncService {

    public void doSomething(){
        System.out.println("=======测试普通任务");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //需要注意的是，开启异步之后就不能去等待返回值。用Future<T>获取返回值
    @Async
    public void doSomethingAsync(){
        System.out.println("=======测试异步任务..");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
