package com.example.demo.config.shiro;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true); // 允许发送cookie
            config.addAllowedOrigin("*"); // 简单请求、非简单请求的跨域设置；表示允许指定规则的url访问
            config.addAllowedOrigin("null");
            config.addAllowedHeader("*"); // 非简单请求跨域处理
            config.addAllowedMethod("*"); // 非简单请求设置，允许访问哪类请求方式（POST、GET...）
            source.registerCorsConfiguration("/**", config); // 非简单请求设置，允许访问哪些接口
            FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
            bean.setOrder(0);
    }
}
