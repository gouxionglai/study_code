package com.gxl.study.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author gouxi
 * @description
 * @since 2020/5/15
 */
public class ConditionNofity implements Runnable{
    private Lock lock;
    private Condition condition;

    public ConditionNofity(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try{
            System.out.println("===begin notify lock====");
            lock.lock();
            try{
                //去阻塞：通知
                System.out.println("===begin notify signal====");
                //把condition队列里面的头部元素唤醒到AQS队列去重新抢占锁
                condition.signal();
                System.out.println("===end notify signal====");
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
            System.out.println("===end notify lock====");
        }
    }
}
