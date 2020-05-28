package com.example.demo.annotation.declare;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @description 定义系统日志注解：主要用于记录登录用户的一些操作信息以及系统日志
 * @author zhushj3
 * @date 2020/05/22
 */
@Target({ METHOD, PARAMETER, FIELD,LOCAL_VARIABLE,TYPE}) // 声明作用域，主要用户方法上
@Retention(RUNTIME)   // 运行时调用
@Documented
public @interface Log {
    String value() default "";
}
