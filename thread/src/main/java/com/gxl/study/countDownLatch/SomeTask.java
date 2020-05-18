package com.gxl.study.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author gouxi
 * @description
 * @since 2020/5/15
 */
public class SomeTask implements Runnable {
    private CountDownLatch countDownLatch;
    public SomeTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("--------"+Thread.currentThread().getName());
        //自减
        countDownLatch.countDown();
    }
}
