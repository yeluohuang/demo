package com.example.demo.annotation.declare;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义系统日志注解：主要用于记录登录用户的一些操作信息以及系统日志
 */
@Target({ METHOD}) // 声明作用域，主要用户方法上
@Retention(RUNTIME)   // 运行时调用
@Documented
public @interface Log {
    String value() default "";
}
