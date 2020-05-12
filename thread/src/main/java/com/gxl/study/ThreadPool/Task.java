package com.gxl.study.ThreadPool;

/**
 * @author gouxi
 * @description
 * @since 2020/5/12
 */
public class Task implements Runnable{
    private int i;
    public Task(int i) {
        this.i = i;
        System.out.println("我是第几只猪："+i);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"号屠夫正在杀猪："+i);
    }
}
