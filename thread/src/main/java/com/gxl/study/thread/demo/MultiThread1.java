package com.gxl.study.thread.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gouxi
 * @description 可见性：增加了volatile修饰的变量，其他线程可见。
 * @since 2020/5/5
 */
public class MultiThread1 {
    static Logger logger = LoggerFactory.getLogger(MultiThread1.class);
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(MultiThread1::loadData,"thread1").start();
        //sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(MultiThread1::refresh,"thread2").start();
    }

    public static void refresh(){
        logger.info("........refresh data start.......");
        flag = true;
        logger.info("........refresh data end.......");
    }


    public static void loadData(){
        while (!flag){
            //do nothing
        }
        logger.info("..............flag changed end...........");
    }
}
