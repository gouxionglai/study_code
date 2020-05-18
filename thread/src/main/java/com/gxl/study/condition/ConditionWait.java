package com.gxl.study.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author gouxi
 * @description
 * @since 2020/5/15
 */
public class ConditionWait implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try{
            System.out.println("===begin wait lock====");
            lock.lock();
            try{
                //阻塞：等待
                System.out.println("===begin wait await====");
                //await之后就会释放当前锁，并且放到Condition队列里面
                condition.await();
                System.out.println("===end wait await====");
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
            System.out.println("===end wait lock====");
        }
    }
}
