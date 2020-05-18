package com.gxl.study.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author gouxi
 * @description 计数器
 * @since 2020/5/15
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        //定义计数器为3
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(new SomeTask(countDownLatch),"线程一").start();//3-1 =2
        new Thread(new SomeTask(countDownLatch),"线程二").start();//2-1 =1
        new Thread(new SomeTask(countDownLatch),"线程三").start(); //1-1= 0

//        new Thread(new SomeTask(countDownLatch),"线程四").start(); //0-1= -1

        //阻塞线程，除非countDownLatch<=0
        countDownLatch.await();
    }
}
