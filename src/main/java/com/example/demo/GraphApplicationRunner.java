package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;

/**
 * @description 应用程序启动后执行，参数由DemoApplication传递过来；可以通过@order注解对该父类的子类实现进行排序执行
 * @author zhushj3
 * @date 2020/04/29
 */
@Component
@Order(1)
public class GraphApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args)  {
    }
}
