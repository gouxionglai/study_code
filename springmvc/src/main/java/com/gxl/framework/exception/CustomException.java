package com.gxl.framework.exception;

import java.io.Serializable;

/**
 * 非业务异常
 * 
 */
public class CustomException extends Exception implements Serializable {

    private String code;
    private String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
