package com.gxl.study.cluster.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author gouxi
 * @description
 * @since 2020/5/19
 */
@ConfigurationProperties(prefix="zookeeper")
@Validated
@Component
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


    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Integer getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(Integer sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public Integer getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(Integer connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(Integer maxRetries) {
        this.maxRetries = maxRetries;
    }

    public Integer getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(Integer baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ZookeeperProperties{");
        sb.append("server='").append(server).append('\'');
        sb.append(", namespace='").append(namespace).append('\'');
        sb.append(", digest='").append(digest).append('\'');
        sb.append(", sessionTimeoutMs=").append(sessionTimeoutMs);
        sb.append(", connectionTimeoutMs=").append(connectionTimeoutMs);
        sb.append(", maxRetries=").append(maxRetries);
        sb.append(", baseSleepTimeMs=").append(baseSleepTimeMs);
        sb.append('}');
        return sb.toString();
    }
}


