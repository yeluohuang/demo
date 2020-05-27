package com.example.demo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication // @Configuration+@EnableAutoConfiguration+@ComponentScan
@MapperScan("com.example.demo.business.**.dao") // 扫描mapper文件夹下面的xml文件，将其与mapper下的接口关联
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.addListeners(new ApplicationPidFileWriter());  // 打印应用的进程信息
        application.run(args);
    }
}
