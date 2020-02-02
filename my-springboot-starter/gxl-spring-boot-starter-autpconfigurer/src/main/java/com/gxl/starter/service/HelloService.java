package com.gxl.starter.service;

/**
 * @author gouxi
 * @description
 * @since 2020/1/31
 */
public class HelloService {

    HelloProperties helloProperties;    //getter/setter

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHello(String name){
        //返回固定的前缀 + 自定义名 + 固定的后缀
        return helloProperties.getPrefix() + name + helloProperties.getSuffix();
    }
}
