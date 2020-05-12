package com.gxl.study.ThreadPool;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gouxi
 * @description 线程池测试
 * @since 2020/5/12
 */
public class ThreadTest {
    public static void main(String[] args) {
        final ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5), Executors.defaultThreadFactory());
        for (int i = 0; i < 8; i++) {
            pool.execute(new Task(i));
        }
        pool.shutdown();
    }
}
