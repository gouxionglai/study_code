package com.gxl.starter.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gouxi
 * @description
 * @since 2020/1/31
 */
@ConfigurationProperties(prefix = "gxl.hello")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
