package com.gxl.study.springboot.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author gouxi
 * @description
 * @since 2020/1/22
 */
@Component
//读指定配置文件 (可以读取多个 数组形式，逗号分隔)
@PropertySource(value={"classpath:config/person.properties"})
//读默认配置文件
@ConfigurationProperties(prefix = "person")
//lombok
@Getter
@Setter
@ToString
public class Person implements Serializable {

    //@Value("${person.name}")
    private String name;
    private Integer age;
    private Byte sex;
    private Date birth;
    private List<Animal> animals;
}
