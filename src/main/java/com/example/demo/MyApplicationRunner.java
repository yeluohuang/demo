package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;

/**
 * 应用程序启动后执行，参数由DemoApplication传递过来；可以通过@order注解对该父类的子类实现进行排序执行
 * @author zhushj3
 * @date 2020/04/29
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("========== 我的springboot框架启动成功 ==========");
        String name  =  ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
    }
}
