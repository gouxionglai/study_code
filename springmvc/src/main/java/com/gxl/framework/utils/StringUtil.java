package com.gxl.framework.utils;

import java.io.Serializable;

/**
 * @author weilai
 * @description
 * @since 2019/12/18
 */
public class StringUtil implements Serializable {
    /**
     * 是否为空
     * @param value
     * @return boolean
     */
    public static boolean isEmpty(String value) {
        if (value == null || "".equals(value.trim())) {
            return true;
        }
        return false;
    }
}
