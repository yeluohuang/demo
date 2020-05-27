package com.example.demo.annotation.declare;

import com.example.demo.annotation.PhoneValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @description 手机号校验注解
 * @author zhushj3
 * @date 2020/04/29
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER }) // 声明作用域
@Retention(RUNTIME)   // 运行时调用
@Documented           // 文档
@Constraint(validatedBy = PhoneValidation.class)   // 声明约束的方法
public @interface Phone {                   // @为注解声明
    boolean required() default true;
    // 错误信息提示语
    String message() default "手机号不合法";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
