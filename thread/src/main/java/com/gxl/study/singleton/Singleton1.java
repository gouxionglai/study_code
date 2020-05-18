package com.gxl.study.singleton;

/**
 * @author gouxi
 * @description 单例模式1:传统的 饥饿模式 懒加载
 * @since 2020/5/14
 */
public class Singleton1 {

    private static Singleton1 instance;

    //构造私有，只能通过提供的方法去获取，不能new
    private Singleton1(){}

    public Singleton1 getInstance(){
        //double check
        if(instance == null){
            synchronized (Singleton1.class){
                if(instance == null){
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}
