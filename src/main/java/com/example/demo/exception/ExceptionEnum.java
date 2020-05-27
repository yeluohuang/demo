package com.example.demo.exception;

/**
 * @description 自定义异常枚举类，罗列接口可能出现的异常信息
 * @author zhushj3
 * @date 2020/04/29
 */
public enum ExceptionEnum implements BaseException {
    // 成功类
    SUCCESS("200","处理成功"),

    // 登录相关
    LOGIN_FAIL("300","登录失败，用户名或密码错误"),
    LOGIN_NO("301","用户未登录"),
    PERMISSION_DENIED("400","无权限操作"),

    // 文件相关
    FILE_NOTFOUND("400","文件不存在"),
    FILE_SIZE_EXCEED("401","文件过大"),

    // redis异常
    REDIS("500","REDIS异常"),

    // 参数类
    EMPTY("999","请求参数为空或包含非法字符"),

    // 未知类
    UNKNOWN("999","服务器未知异常")
   ;
    private String code;
    private String msg;

    ExceptionEnum(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
