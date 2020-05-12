package com.gxl.study.AQS.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author gouxi
 * @description
 * @since 2020/5/11
 */
public class UnsafeInstance {
    /**
     * 绕过虚拟机，直接操作底层内存
     * 只能通过反射去获取
     * @return
     */
    public static Unsafe reflectGetUnsafe(){
        try{
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            ((Field) field).setAccessible(true);
            return (Unsafe) field.get(null);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
