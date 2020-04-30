package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 全局配置信息（在配置文件之外的配置）:静态函数需要从配置文件读取的信息
 */
@Component
public class GlobalProperties {
    @Autowired
    private Environment environment;

    /**
     * 该注解可以保证所有依赖已经注入，实例都已经初始化后调用声明的方法
     */
    @PostConstruct
    public void initConfig() {
        // 读取配置文件
    }
}
