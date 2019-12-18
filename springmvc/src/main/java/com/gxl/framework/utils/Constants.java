package com.gxl.framework.utils;

import java.io.Serializable;

/**
 * 常量类
 */
public class Constants implements Serializable {

    public final static String SESSION_USER = "SESSION_USER";
    public final static String SESSION_CKFINDER_ROLE = "CKFinder_UserRole";


    public final static String SESSION_USER_RIGHT = "SESSION_USER_RIGHT";
    public final static String PASSWORD_INIT = "password.init";
    public final static String OUT_WRITE_CSV_FILENAME = "out.write.csv.fileName";

    public final static String EXPORT_FILE_SEPRATOR = ",";
    public final static String EXPORT_FILE_TITLE_SEPRATOR = ",";
    public final static int EXPORT_FILE_POLL_LIMIT = 100;
    // 超过5000条就提示
    public final static int EXPORT_FILE_ALERT_LIMIT = 5000;

    //=======================申请名称常量================================
    /**
     * 发文会签申请
     */
    public final static String WF_JOINT_SIGN_APPLY = "发文会签申请";

    /**
     * 请假申请
     */
    public final static String WF_LEAVE_APPLY = "请假申请";


}
