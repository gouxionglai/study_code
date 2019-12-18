package com.gxl.framework.model;

import net.sf.json.JSONObject;

import java.io.Serializable;

/**
 * 返回客户端统一格式.包括状态码,提示信息,请求URL,以及业务数据
 *
 */
public class MessageResult implements Serializable{
    //状态码
    private String code;
    //必要的提示信息
    private String message;
    
    private String url;
    
    //业务数据
    private Object data;
    
    public MessageResult(String code,String message){
        this.code = code;
        this.message = message;
        this.url="";
        this.data=new Object();
    }
    
    public MessageResult(String code,String message,String url){
        this.code = code;
        this.message = message;
        this.url=url;
        this.data=new Object();
    }

    public MessageResult(String code,String message,String url,Object data){
        this.code = code;
        this.message = message;
        this.url=url;
        this.data = data;
    }
   
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String toString(){
        if(null == this.data){
            this.setData(new Object());
        }
        return JSONObject.fromObject(this).toString();
    }

}
