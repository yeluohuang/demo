package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * springboot 实例化对象工具类,非注入IOC的方式
 * @author zhushj3
 * @date 2020/05/22
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        checkApplicationContext();
        return applicationContext;
    }

    public static <T> T getBean(String name){
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        checkApplicationContext();
        return (T) applicationContext.getBeansOfType(clazz);
    }

    public static void cleanApplicationContext(){
        applicationContext = null;
    }

    private static void checkApplicationContext(){
        if(applicationContext ==null){
            if (applicationContext == null){
                throw new IllegalStateException("applicationContext未注入，请在applicationContext.xml中定义SpringContext");
            }
        }
    }

    @Override
    public void destroy() throws Exception {

    }
}
