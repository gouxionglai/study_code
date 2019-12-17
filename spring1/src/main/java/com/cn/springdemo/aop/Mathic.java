package com.cn.springdemo.aop;

import org.springframework.stereotype.Repository;

/**
 * @author weilai
 * @description
 * @since 2019/10/15
 */
@Repository(value="mathic")
public class Mathic {
//    private int i;
//    private int j;

    public int add(int i, int j) {
        return i + j;
    }

    public int sub(int i, int j) {
        return i - j;
    }

    public int multi(int i, int j) {
        return i * j;
    }

    //除法
    public int div(int i, int j) {
        return i / j;
    }
}
