package com.example.demo.business.controller;

import com.example.demo.business.pojo.Test;
import com.example.demo.business.pojo.TestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Enumeration;

/**
 * 主要用于测试http网络通信协议
 * @author zhushj3
 * @date 2020/04/28
 */
@RestController
public class HttpTestController {
    public static final Logger logger = LoggerFactory.getLogger(HttpTestController.class);
    /**
     * 利用原生servlet的方式处理请求，并返回信息
     * @param request 请求体
     * @param response 返回体
     */
    @PostMapping("/headers_print")
    public TestResponse getHeader(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            String body = request.getHeader(header);
            logger.info("    请求头信息："+header+"="+body);
        }
        logger.info("其他信息：requestUri"+  request.getRequestURI());  // 当前的接口名
        logger.info("其他信息：requestUrl="+  request.getRequestURL()); // 当前请求url
        logger.info("其他信息：authType="+  request.getAuthType());
        logger.info("其他信息：contextPath="+  request.getContextPath());
        // 获取客户端得到发送的数据
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()){
            System.out.println("客户端的数据");
            String name = params.nextElement();
            System.out.println(name+"="+request.getParameter(name));
        }
        // 设置返回体信息
        response.setContentType("application/json;charset=utf-8");
        return TestResponse.ok("打印信息成功");
    }

    @PostMapping("/test")
    public TestResponse testSwagger(@RequestBody Test test){
        Test temp = new Test();
        temp.setUsername("朱少杰");
        temp.setUserId("303406");
        temp.setDate(new Date());
        return TestResponse.ok("测试成功",temp);
    }
}
