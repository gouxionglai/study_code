package com.gxl.framework.handler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息对工具类
 */
public class MessagePair implements Serializable {

    private static final long serialVersionUID = -9182513775001007252L;

    /**
     * 成功获取
     */
    public static final String STATUS_CODE_SUCCESS = "0";

    /**
     * 无对应的数据S
     */
    public static final String STATUS_CODE_JSON_NO_DATA_ERROR = "-4";

    /**
     * 数据解密错误
     */
    public static final String STATUS_CODE_JSON_DECRYPT_ERROR = "-5";

    /**
     * 数据格式错误
     */
    public static final String STATUS_CODE_JSON_FORMAT_ERROR = "-6";

    /**
     * 参数缺失或类型错误
     */
    public static final String STATUS_CODE_JSON_PARAM_ERROR = "-7";
    /**
     * 会话过期
     */
    public static final String STATUS_CODE_JSON_SESSION_TIMEOUT = "-8";
    /**
     * 系统繁忙，请稍后重试
     */
    public static final String STATUS_CODE_JSON_SYSTEM_EXCEPTION = "-9";

    /**
     * Bad Request|请求参数有误
     */
    protected static final String STATUS_CODE_HTTP_BAD_REQUEST = "400";

    /**
     * Unauthorized|非授权
     */
    protected static final String STATUS_CODE_HTTP_UNAUTHORIZED = "401";
    /**
     * Forbidden|禁用
     */
    protected static final String STATUS_CODE_HTTP_FORBIDDEN = "403";
    /**
     * Not Found|请求资源无效
     */
    protected static final String STATUS_CODE_HTTP_NOT_FOUND = "404";
    /**
     * Method Not Allowed|请求方式错误
     */
    protected static final String STATUS_CODE_HTTP_METHOD_NOT_ALLOWED = "405";
    /**
     * Not Acceptable|头信息不合法
     */
    protected static final String STATUS_CODE_HTTP_NOT_ACCEPT = "406";
    /**
     * Internal Server Error|服务器忙，请稍后再试
     */
    protected static final String STATUS_CODE_HTTP_INTERNAL_SERVER_ERROR = "500";

    /**
     * RuntimeException|运行时异常
     */
    protected static final String STATUS_CODE_SERVER_RUNTIME = "1000";

    /**
     * NullPointerException|空值异常
     */
    protected static final String STATUS_CODE_SERVER_NULL_POINT = "1001";
    /**
     * ClassCastException|数据类型转换异常
     */
    protected static final String STATUS_CODE_SERVER_CLASS_CAST = "1002";

    /**
     * IOException|IO异常
     */
    protected static final String STATUS_CODE_SERVER_IO = "1003";
    /**
     * NoSuchMethodException|未知方法异常
     */
    protected static final String STATUS_CODE_SERVER_NO_SUCH_METHOD = "1004";

    /**
     * IndexOutOfBoundsException|数组越界异常
     */
    protected static final String STATUS_CODE_SERVER_INDEX_OUT_OF_BOUND = "1005";

    /**
     * BUSINESS|2000|用户未注册
     */
    public static final String STATUS_CODE_BUSINESS_USER_NOT_REGISTER = "2000";


    //安卓端错误代码
    //platform
    public static final String STATUS_CODE_USER_LOGIN_ERROR = "3000";
    public static final String STATUS_CODE_USER_LOCKED = "3001";
    /**
     * VEHICLE|3002|车辆使用状态不正确
     */
    public static final String STATUS_CODE_VEHICLE_USE_STATUS_ERROR = "3002";
    /**
     * VEHICLE|3003|车辆出行记录状态不正确
     */
    public static final String STATUS_CODE_VEHICLE_USERECORD_STATUS_ERROR = "3003";
    /**
     * VEHICLE|3004|车辆出行检查未通过
     */
    public static final String STATUS_CODE_VEHICLE_CHECK_STATUS_ERROR = "3004";
    public static final String STATUS_CODE_VEHICLE_USEMILEAGE_ERROR = "3005";

    /**
     * 修改失败
     */
    public static final String UPDATE_FAILD = "3006";

    /**
     * TECHQUALITY_CHECK|3007|技术质量检查状态不正确
     */
    public static final String STATUS_CODE_TECHQUALITY_CHECK_STATUS_ERROR = "3007";

    /**
     * WORKFLOW_NEXTPEOPLE|3008|提交申请时，请选择下一步执行人！
     */
    public static final String STATUS_CODE_WORKFLOW_NEXTPEOPLE_ERROR = "3008";
    /**
     * 未查询到该项目的档案信息，请重新选择！
     */
    public static final String STATUS_CODE_PROJECT_FILE_NOT_FIND = "3009";

    /**
     * 参数为空
     */
    public static final String STATUS_CODE_PARAMETER_IS_EMPTY = "3010";

    /**
     * 当天已经添加过日志，不允许重复添加 （安全日志和施工日志共用）
     */
    public static final String STATUS_CODE_IS_LOGGED_ERRO = "3011";

    /**
     * 车辆维保类型不正确
     */
    public static final String STATUS_CODE_VEHICLE_REPAIR_TYPE_ERRO = "3012";
    /**
     * 发文会签：班子成员会签不能只选择一个领导
     */
    public static final String STATUS_CODE_POSTSIGN_MULTIUSER_ERROR = "3013";

    /**
     * 转存失败，个人网盘空间不足！
     */
    public static final String STATUS_CODE_CLOUD_DISK_NOT_ENOUGH_SPACE = "3014";

    /**
     * 个人网盘初始化失败
     */
    public static final String STATUS_CODE_DISK_INIT_FAILED = "3015";


    /**
     * 修改失败，请重试！
     */
    public static final String STATUS_CODE_UPDATE_FAILED = "3016";

    /**
     * 原密码错误，请核对后重新输入！
     */
    public static final String STATUS_CODE_OLD_PASSWD_ERROR = "3017";

    /**
     * 添加失败，请重试！
     */
    public static final String STATUS_CODE_ADD_FAILED = "3018";

    /**
     * 删除失败，请稍候重试！
     */
    public static final String STATUS_CODE_DELETE_FAILED = "3019";


    /**
     * ==========================
     * 小程序错误代码
     */

    /**
     * 验证码已过期，请重新获取！
     */
    public static final String STATUS_CODE_WECHAT_MINI_CHECK_CODE_LOSE = "4001";

    /**
     * 绑定手机号异常！
     */
    public static final String STATUS_CODE_WECHAT_MINI_PHONE_BIND_ERROR = "4002";

    /**
     * 验证码错误，请核对后重新输入！
     */
    public static final String STATUS_CODE_WECHAT_MINI_CHECK_CODE_ERROR = "4003";
    /**
     * 无匹配数据！
     */
    public static final String STATUS_CODE_WECHAT_MINI_NO_MATCH_DATA = "4004";

    /**
     * 客户工单创建失败，请重试！
     */
    public static final String STATUS_CODE_WECHAT_MINI_CREATE_TICKET_FAILD = "4005";

    /**
     * 获取工单信息失败，请重试！
     */
    public static final String STATUS_CODE_WECHAT_MINI_GET_TICKET_INFO_FILED = "4006";

    /**
     * 回复工单失败，请稍候重试！
     */
    public static final String STATUS_CODE_WECHAT_MINI_REPLY_TICKET_FILED = "4007";

    /**
     * 工单关闭失败，请稍候重试！
     */
    public static final String STATUS_CODE_WECHAT_MINI_TICKET_CLOSE_FILED = "4008";

    /**
     * 问卷提交失败，请稍候重试！
     */
    public static final String STATUS_CODE_WECHAT_MINI_EXAMINATION_ADD_FILED = "4009";

    private static Map<String, String> messageMap = new HashMap<String, String>();


    // 初始化状态码与文字说明
    static {
        messageMap.put("0", "成功");
        messageMap.put("-4", "无对应的数据");
        messageMap.put("-5", "数据解密错误");
        messageMap.put("-6", "数据格式错误");
        messageMap.put("-7", "参数缺失或类型错误");
        messageMap.put("-8", "会话过期");
        messageMap.put("-9", "系统繁忙，请稍后重试");

        messageMap.put("400", "HTTP|400|Bad Request|请求参数有误");
        messageMap.put("401", "HTTP|401|Unauthorized|非授权");
//        messageMap.put("402", "HTTP|402|Payment Required|");// 该状态码是为了将来可能的需求而预留的。
        messageMap.put("403", "HTTP|403|Forbidden|禁用");
        messageMap.put("404", "HTTP|404|Not Found|请求资源无效");
        messageMap.put("405", "HTTP|405|Method Not Allowed|请求方式错误");
        messageMap.put("406", "HTTP|406|Not Acceptable|头信息不合法");
        messageMap.put("500", "HTTP|500|Internal Server Error|服务器忙，请稍后再试");

        messageMap.put("1000", "SERVER|1000|RuntimeException|运行时异常");
        messageMap.put("1001", "SERVER|1001|NullPointerException|空值异常");
        messageMap.put("1002", "SERVER|1002|ClassCastException|数据类型转换异常");
        messageMap.put("1003", "SERVER|1003|IOException|IO异常");
        messageMap.put("1004", "SERVER|1004|NoSuchMethodException|未知方法异常");
        messageMap.put("1005", "SERVER|1005|IndexOutOfBoundsException|数组越界异常");
//        messageMap.put("1006", "SERVER|网络异常");

        messageMap.put("2000", "BUSINESS|2000|用户未注册");
        messageMap.put("2001", "BUSINESS|2001|用户已注册");
        messageMap.put("2002", "BUSINESS|2002|用户名或密码错误");
        messageMap.put("2003", "BUSINESS|2003|用户帐号冻结");
        messageMap.put("2004", "BUSINESS|2004|会话超期，请重新登录");

//        messageMap.put("1020", "BUSINESS|验证码发送失败");
//        messageMap.put("1021", "BUSINESS|验证码失效");
//        messageMap.put("1022", "BUSINESS|验证码错误");
//        messageMap.put("1023", "BUSINESS|验证码不可用");
//        messageMap.put("1029", "BUSINESS|短信平台异常");

//        messageMap.put("2010", "BUSINESS|缺少参数或值为空");
//        
//        messageMap.put("2029", "BUSINESS|参数不合法");
//        messageMap.put("2020", "BUSINESS|无效的Token");
//        messageMap.put("2021", "BUSINESS|无操作权限");
//        messageMap.put("2022", "BUSINESS|RSA解密失败,密文数据已损坏");
//        messageMap.put("2023", "BUSINESS|请重新登录");

        /**
         * 安卓端异常code
         */
        //platform
        messageMap.put("3000", "用户名或密码错误");
        messageMap.put("3001", "用户帐号冻结");
        messageMap.put("3002", "车辆使用状态不正确");
        messageMap.put("3003", "车辆出行记录状态不正确");
        messageMap.put("3004", "车辆出行检查未通过");
        messageMap.put("3005", "车辆出行起始里程不能大于结束里程");
        messageMap.put("3006", "修改失败");
        messageMap.put("3007", "技术质量检查状态不正确");
        messageMap.put("3008", "提交申请时，请选择下一步执行人");
        messageMap.put("3009", "未查询到该项目的档案信息，请重新选择！");
        messageMap.put("3010", "参数不能为空！");
        messageMap.put("3011", "当天已经添加过日志，不允许重复添加");
        messageMap.put("3012", "车辆维保类型不正确");
        messageMap.put("3013", "班子成员会签不能只选择一个领导");
        messageMap.put("3014", "转存失败，个人网盘空间不足！");
        messageMap.put("3015", "用户个人网盘基础信息初始化异常，请重新访问个人网盘后重试！");
        messageMap.put("3016", "修改失败，请重试！");
        messageMap.put("3017", "原密码错误，请核对后重新输入！");
        messageMap.put("3018", "添加失败，请稍候重试！");
        messageMap.put("3019", "删除失败，请稍候重试！");

        /**
         * 小程序异常错误
         */
        messageMap.put("4001", "验证码已过期，请重新获取！");
        messageMap.put("4002", "绑定手机号异常！");
        messageMap.put("4003", "验证码错误，请核对后重新输入！");
        messageMap.put("4004", "无匹配数据！");
        messageMap.put("4005", "客户工单创建失败，请重试！");
        messageMap.put("4006", "获取工单信息失败，请重试！");
        messageMap.put("4007", "回复工单失败，请稍候重试！");
        messageMap.put("4008", "工单关闭失败，请稍候重试！");
        messageMap.put("4009", "问卷提交失败，请稍候重试！");
    }

    /**
     * 通过code获取消息内容
     *
     * @param code
     * @return
     */
    public static String getMessage(String code) {

        return messageMap.get(String.valueOf(code));
    }

}
