package com.gxl.study.singleton;

/**
 * 推荐使用方式
 * @author gouxi
 * @description 利用了static在启动的时候只加载一次，所以不会有线程安全问题。并且在使用Singleton3的才加载
 * 不使用锁来控制
 * @since 2020/5/14
 */
public class Singleton3 {

    //私有防止new
    private Singleton3(){}

    private static class Instance_Holder{
        private final static Singleton3 instance = new Singleton3();

    }


    public Singleton3 getInstance(){
        return Instance_Holder.instance;
    }
}
