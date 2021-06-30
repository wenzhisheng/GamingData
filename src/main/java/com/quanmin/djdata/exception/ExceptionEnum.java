package com.quanmin.djdata.exception;

/**
 * @Author: ate
 * @Description: 异常枚举
 * @CreateDate: 2019-11-11 16:58
 * @ClassName: com.quanmin.djdata.exception.ExceptionEnum
 */
public enum ExceptionEnum {
    UNKNOW_ERROR(-1, "系统出现异常！"),
    USER_NOT_FIND(-2, "数据库插入数据异常！"),
    NOT_LOGIN_ERROR(401, "您的账号未登录，请登录后再操作！"),
    SINGLE_LOGIN_ERROR(3, "该账号已在其他地方登录，您被挤下线了，请重新登录。"),
    PARAM_ERROR(0, "请求参数非法！"),
    STATUS_407(407, "请求的域名无效"),
    UNKNOW_URL(-4, "请求的域名无效");

    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
