package com.example.demo.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description Xss过滤器,对所有url进行过滤
 * @author zhushj3
 * @date 2020/05/21
 */
@WebFilter(filterName="XssFilter", urlPatterns="/*")
public class XssFilter implements Filter{
    public FilterConfig filterConfig = null;
    private Logger logger = LoggerFactory.getLogger(XssFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("XssFilter 初始化完毕");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("XssFilter 开始过滤");
        // 对入参和出参进行过滤或者包装
        filterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest)servletRequest), servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("XssFilter 销毁完毕");
        this.filterConfig = null;
    }
}
