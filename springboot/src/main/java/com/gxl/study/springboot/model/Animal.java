package com.gxl.study.springboot.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author gouxi
 * @description
 * @since 2020/1/22
 */
@Getter
@Setter
@ToString
public class Animal implements Serializable {

    private String name;
    private String age;
    private String sign;
}
