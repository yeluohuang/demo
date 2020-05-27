package com.example.demo.exception;

/**
 * @description 自定义业务类异常
 * @author zhushj3
 * @date 2020/04/29
 */
public class TestException extends RuntimeException implements BaseException{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String msg;

    public TestException() {
        super();
    }

    public TestException(String errorMsg) {
        super(errorMsg);
        this.msg= errorMsg;
    }

    public TestException(BaseException errorInfoInterface) {
        super(errorInfoInterface.getCode());
        this.code = errorInfoInterface.getCode();
        this.msg= errorInfoInterface.getMsg();
    }

    public TestException(BaseException errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getCode(), cause);
        this.code = errorInfoInterface.getCode();
        this.msg= errorInfoInterface.getMsg();
    }

    public TestException(String code, String errorMsg) {
        super(code);
        this.code = code;
        this.msg= errorMsg;
    }

    public TestException(String code, String errorMsg, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.msg= errorMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
