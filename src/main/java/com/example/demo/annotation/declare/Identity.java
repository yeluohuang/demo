package com.example.demo.annotation.declare;

import com.example.demo.annotation.IdentityValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 身份证号校验注解：
 * 只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型和
 * String、Enum、Class、annotations等数据类型,以及这一些类型的数组
 *
 * @author zhushj3
 * @date 2020/04/29
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER }) // 声明作用域，从左至右依次是：方法、属性、注解类型、构造器、参数；此外还包括：包、局部变量、类/接口/枚举
@Retention(RUNTIME)   // 指明生存周期：SOURCE，源码级别保留，编译后即丢弃；CLASS，默认属性，编译后的class文件中存在，在jvm运行时丢弃；RUNTIME： 运行级别保留，编译后的class文件中存在，在jvm运行时保留，可以被反射调用。
@Documented           // 指明修饰的注解，可以被例如javadoc此类的工具文档化，只负责标记，没有成员取值
@Constraint(validatedBy = IdentityValidation.class)   // 声明约束的方法
public @interface Identity {
    boolean required() default true;
    // 错误信息提示语
    String message() default "身份证号不合法";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
