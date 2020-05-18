package com.gxl.study.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gouxi
 * @description 初步认识Condition的使用
 * @since 2020/5/15
 */
public class Demo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Lock lock2 = new ReentrantLock(true);
        Condition condition = lock.newCondition();

        //await之后会加入队列
        new Thread(new ConditionWait(lock,condition)).start();
        new Thread(new ConditionWait(lock,condition)).start();
        //signal会从队列里面取第一个出来继续执行
        new Thread(new ConditionNofity(lock,condition)).start();
    }
}
