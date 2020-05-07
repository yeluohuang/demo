package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 主入口，此时需要声明扫描的mapper接口（其他配置会自动扫描）
@SpringBootApplication
@MapperScan("com.example.demo.**.dao") // 扫描mapper文件夹下面的xml文件，将其与mapper下的接口关联
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
