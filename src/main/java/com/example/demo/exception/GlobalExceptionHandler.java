package com.example.demo.exception;

import com.example.demo.business.test.pojo.TestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @description 全局异常处理器:需要声明@ControllerAdvice
 * @author zhushj3
 * @date 2020/04/29
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 对自定义的业务异常进行处理,并封装异常信息返回给客户端
     * @param e  接收到的异常
     * @return
     */
    @ExceptionHandler(value =TestException.class)
    @ResponseBody
    public TestResponse exceptionHandler(BaseException e){
        logger.error("业务异常："+e.getMsg());
        return TestResponse.error(e);
    }

    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public TestResponse exceptionHandler(HttpServletRequest req, SQLException e){
        logger.error("数据库处理异常:",e);
        return TestResponse.error("数据库异常");
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseBody
    public TestResponse exceptionHandler(HttpServletRequest req, IOException e){
        logger.error("流异常:",e);
        return TestResponse.error("文件/字节流异常");
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    @ResponseBody
    public TestResponse exceptionHandler(HttpServletRequest req, FileNotFoundException e){
        logger.error("文件不存在:",e);
        return TestResponse.error(ExceptionEnum.FILE_NOTFOUND);
    }

    /**
     * 其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public TestResponse exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常:",e);
        return TestResponse.error(ExceptionEnum.UNKNOWN);
    }
}
