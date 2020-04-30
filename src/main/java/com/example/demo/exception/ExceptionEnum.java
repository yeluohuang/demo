package com.example.demo.exception;

import org.omg.CORBA.UNKNOWN;

/**
 * 自定义异常枚举类，罗列接口可能出现的异常信息
 * @author zhushj3
 * @date 2020/04/29
 */
public enum ExceptionEnum implements BaseException {
    // 成功类
    SUCCESS("200","处理成功"),
    // 业务类：暂无
    FILE_NOTFOUND("300","文件不存在"),
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
