package com.gxl.study.cluster.service;

/**
 * @author gouxi
 * @description
 * @since 2020/5/19
 */
public interface SRLockDealCallback<T> {
    /**
     * 获取可重入共享锁后的处理方法
     * @return
     */
    public T deal();
}
