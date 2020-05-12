package com.gxl.study.thread.demo;

/**
 * @author gouxi
 * @description  原子性：volatile不能保证原子性，因为cacheline有大小限制。
 * @since 2020/5/5
 */
public class MutilThread2 {
    private static volatile int count = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    //count++不是原子性操作
                    //1.读count的值
                    //2.自增
                    count++;
                }
            }).start();
        }
        System.out.println(count);
    }
}
