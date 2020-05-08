package com.example.demo.business.test.pojo;

import com.example.demo.exception.BaseException;

/**
 * 测试类返回体包装对象,用于封装接口返回的数据
 * @author zhushj3
 * @date 2020/04/28
 */
public class TestResponse {
    private static final String RESULT_OK = "200";
    private static final String RESULT_ERROR = "999";
    private String msg;
    private String code;
    private Object data;

    public TestResponse(String code, String msg, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public TestResponse(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }
    public static TestResponse error(String msg){
        return new TestResponse(RESULT_ERROR,msg);
    }
    public static TestResponse error(String code, String msg){
        return new TestResponse(code,msg);
    }
    /**
     * 错误类返回体
     * @param exceptionEnum  异常枚举类
     * @return
     */
    public static TestResponse error(BaseException exceptionEnum){
        return new TestResponse(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }
    public static TestResponse ok(String msg){
        return new TestResponse(RESULT_OK,msg);
    }
    public static TestResponse ok(String msg, Object object){
        return new TestResponse(RESULT_OK,msg,object);
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestResponse{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
