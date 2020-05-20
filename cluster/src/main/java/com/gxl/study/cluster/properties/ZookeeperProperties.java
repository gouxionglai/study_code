package com.gxl.study.cluster.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author gouxi
 * @description
 * @since 2020/5/19
 */
@ConfigurationProperties(prefix="zookeeper")
@Data
@Validated
public class ZookeeperProperties {

    @NotNull(message = "zookeeper服务地址不能为空")
    private String server;
    @NotNull(message = "namespace不能为空")
    private String namespace;
    private String digest;
    private Integer sessionTimeoutMs = 60000;
    private Integer connectionTimeoutMs = 6000;
    private Integer maxRetries = 3;
    private Integer baseSleepTimeMs = 1000;
}


