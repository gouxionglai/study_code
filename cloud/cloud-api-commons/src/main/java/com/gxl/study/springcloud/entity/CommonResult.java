package com.gxl.study.springcloud.entity;

/**
 * @author gouxi
 * @description
 * @since 2020/6/30
 */
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CommonResult() {

    }
    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CommonResult{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
