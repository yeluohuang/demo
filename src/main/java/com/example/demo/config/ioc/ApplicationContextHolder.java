package com.example.demo.config.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description springboot 实例化对象工具类，创建的对象将注入容器
 * @author zhushj3
 * @date 2020/05/22
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);
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
        if(clazz != null) {
            logger.error("创建新的对象，注入容器：" + clazz.getName());
        }
        return (T) applicationContext.getBeansOfType(clazz);
    }

    public static void cleanApplicationContext(){
        applicationContext = null;
    }

    private static void checkApplicationContext(){
        if(applicationContext ==null){
            if (applicationContext == null){
                logger.error("applicationContext 未注入");
                throw new IllegalStateException("applicationContext未注入，请在applicationContext.xml中定义SpringContext");
            }
        }
    }

    @Override
    public void destroy() throws Exception {

    }
}
