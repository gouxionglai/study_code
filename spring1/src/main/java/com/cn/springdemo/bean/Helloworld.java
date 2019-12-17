package com.cn.springdemo.bean;

/**
 * @author weilai
 * @description hello world
 * @since 2019/10/14
 */
public class Helloworld {

    private String name;
    private String description;


    public Helloworld() {
        System.out.println("constructor init...");
    }

    public String getName() {
        return name;
    }

    //自动注入是根据setXX来的
    public void setName2(String name) {
        System.out.println("set name...");
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        System.out.println("set description...");
        this.description = description;
    }

    public void hello(){
        System.out.println("hello "+ name);
    }

    public void init(){
        System.out.println("hello bean inited");
    }
    public void destroy(){
        System.out.println("hello bean destroyed");
    }
}
