package com.gxl.framework.utils;

import java.io.Serializable;

/**
 * web常量类
 */
public class WebConstants implements Serializable {
    /**
     * 每页显示的行数
     */
    public final static Integer PAGE_SIZE = 30;

    /**
     * 每页显示的行数(针对移动端分页使用)
     */
    public final static Integer PAGE_SIZE_MOBILE = 8;

    /**
     * 每页显示的行数
     */
    public final static int PAGING_SIZE = 20;

    public final static int MOBILE_PAGING_MAX_SIZE = 25;

    public final static String MESSAGE_ALERT_TITLE = "actionMessage";

    /**
     * 每页最小行数
     */
    public final static int PAGING_SIZE_LOW = 5;

    public static final String CLIENT_APK_DIR = "client.apk.dir";

    public static final String PROPERTIES_FILE_NAME = "message";
    public static final String BUSINESS_FILE_SAVE_DIR = "business.file.save.dir";
    public static final String PROJECT_FILE_SAVE_DIR = "project.file.save.dir";
    public static final String OA_FILE_SAVE_DIR = "oa.file.save.dir";
    /**
     * 供应商模块
     */
    public static final String SUPPLIER_FILE_SAVE_DIR = "supplier.file.save.dir";
    /**
     * 固定资产
     */
    public static final String ASSETS_FILE_SAVE_DIR = "assets.file.save.dir";
    public static final String TEMPLATE_FILE_SAVE_DIR = "template.file.save.dir";
    public static final String CLOUDDISK_FILE_SAVE_DIR = "clouddisk.file.save.dir";
    public static final String IM_FILE_SAVE_DIR = "im.file.save.dir";
    public static final String TMP_FILE_SAVE_DIR = "tmp.file.save.dir";

    public static final String CLOUDDISK_USER_STORAGE_SIZE = "clouddisk.userstorage.size";

    public static final String USER_PHOTO_ACCESS_BASE_RELATIVE_URL = "user.photo.save.base.relative.dir";

    public static final String WEBSITE_LINK_ACCESS_BASE_RELATIVE_URL = "website.link.save.base.relative.dir";

    public static final String WEBSITE_ARTICLE_ACCESS_BASE_RELATIVE_URL = "website.article.save.base.relative.dir";

    public static final String IMAGE_ACCESS_BASE_URL = "image.access.base.url";

    public static final String FILE_ACCESS_BASE_URL = "file.access.base.url";

    public static final String BACKEND_ACCESS_BASE_URL = "backend.access.base.url";


    public static final String INIT_PASSWORD = "password.init";

    public static final String ENCRYPT_FLG = "encrypt.flg";
    public static final String RSA_PUBLIC_KEY_PATH = "rsa.public.key.path";
    public static final String RSA_PRIVATE_KEY_PATH = "rsa.private.key.path";

    public final static String JSON_KEY_CODE = "code";
    public final static String JSON_KEY_MESSAGE = "message";
    public final static String JSON_KEY_URL = "url";
    public final static String JSON_KEY_DATA = "data";
    public final static String JSON_KEY_KEYWORD_CONDITION = "keyword_condition";
    public final static String JSON_KEY_OFFSET = "offset";
    public final static String JSON_KEY_ROWCOUNT = "row_count";

    public final static String FILE_PATH_AND_NAME_SPLIT_STR = ",";

    // 压缩图片的高度
    public final static int IMAGE_WIDTH = 128;
    public final static int IMAGE_HEIGHT = 128;


    //项目首页图片
    public static final String PROJECT_IMAGE_ACCESS_BASE_RELATIVE_URL = "project.image.save.base.relative.dir";


    //umeng  推送
    public final static String UMENG_PUSH_APP_KEY_ANDROID = "umeng.push.app.key.android";
    public final static String UMENG_PUSH_APP_MASTER_SECRET_ANDROID = "umeng.push.app.master.secret.android";
    public final static String UMENG_PUSH_APP_KEY_IOS = "umeng.push.app.key.ios";
    public final static String UMENG_PUSH_APP_MASTER_SERRET_IOS = "umeng.push.app.master.secret.ios";
    public final static String UMENG_PUSH_COMMON_TICKER = "umeng.push.common.ticker";

    //融云
    public final static String RONG_YUN_APP_KEY = "rongyun.appkey";
    public final static String RONG_YUN_APP_SECRET = "rongyun.appsecret";
    public final static String RONG_YUN_API = "http://api.cn.ronghub.com";

}


