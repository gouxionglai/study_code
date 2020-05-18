package com.gxl.study.singleton;

/**
 * @author gouxi
 * @description 单例模式2:传统的 饥饿模式 懒加载
 * 增加volatile
 * @since 2020/5/14
 */
public class Singleton2 {

    //增加volatile标记，多线程情况下可见
    private static volatile Singleton2 instance;

    //构造私有，只能通过提供的方法去获取，不能new
    private Singleton2(){}

    public Singleton2 getInstance(){
        //double check
        if(instance == null){
            synchronized (Singleton2.class){
                if(instance == null){
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
