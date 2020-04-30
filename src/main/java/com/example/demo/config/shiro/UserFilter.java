package com.example.demo.config.shiro;

import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器:参考官网查看不同过滤器的作用，此处为了对请求信息做一个增强处理
 * 注意：login接口不会进入该过滤器，而是直接进入接口进行逻辑处理
 *
 * @author zhushj3
 * @date 2020/04/30
 */
public class UserFilter extends org.apache.shiro.web.filter.authc.UserFilter {
    // 类似于AOP中的前置增强；在拦截器链执行之前执行；如果返回true则继续拦截器链；否则中断后续的拦截器链的执行直接返回；进行预处理（如基于表单的身份验证、授权）
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        boolean isAccess = false;
        HttpServletResponse response1 = (HttpServletResponse) response;
        HttpServletRequest request1 = (HttpServletRequest) request;
        // 是否是非简单请求
        if(request1.getMethod().equals(RequestMethod.OPTIONS.name())){
            isAccess = true;
        } else if(request1.getMethod().equals(RequestMethod.POST.name())){
            isAccess = true;
        } else if(request1.getMethod().equals(RequestMethod.GET.name())){
            isAccess = true;
        }
        setHeader(request1,response1);
        return isAccess;
    }

    // 跨域请求
    private void setHeader(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-control-Allow-Origin",request.getHeader("Origin")); // 请求的域名
        response.setHeader("Access-control-Allow-Method",request.getMethod()); // 非简单请求（put,delete,正式通信前发送一次请求），需要返回该参数（浏览器需要用到的的请求类型）
        response.setHeader("Access-control-Allow-Credentials","true"); // 允许发送cookie
    }
}
