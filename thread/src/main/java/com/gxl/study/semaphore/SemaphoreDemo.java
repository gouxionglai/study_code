package com.gxl.study.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author gouxi
 * @description
 * @since 2020/5/15
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //定义5个车位
        Semaphore semaphore = new Semaphore(5);
        //10辆车
        for (int i = 0; i < 10; i++) {
            new Car(i,semaphore).start();
        }
    }
    static class Car extends Thread{
        private int num;
        private Semaphore semaphore;

        public Car(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                //获取令牌，如果拿不到会阻塞
                semaphore.acquire();
                System.out.println("===="+num+"停车===");
                Thread.sleep(2000);
                System.out.println("===="+num+"开走了===");
                //释放锁
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
