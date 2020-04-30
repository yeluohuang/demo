package com.example.demo.aspect;



import com.example.demo.annotation.declare.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/** 面向切面的配置：记录系统日志，存储于数据库中
 * @author zhushj3
 * @date 2020/04/28
 */
@Aspect
@Component
public class LogAspect {
    /**
     * slf4j日志
     */
    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * Pointcut 切入点,即需要执行切面编程的类或者函数
     * 匹配日志注解
     */
    @Pointcut("@annotation(com.example.demo.annotation.declare.Log)")
    public void webLog(){}

    /**
     * 环绕通知：在执行函数的过程中进行操作:此处可以进行全局异常处理，或者交由全局异常处理器处理
     */
    @Around(value = "webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取切点的对象信息
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        String msg = log.value();
        // 测试
        logger.info("测试Log注解"+msg);
        return pjp.proceed();
    }

    /**
     * 方法执行前
     */
    @Before(value = "webLog()")
    public void before(JoinPoint joinPoint){
    }

    /**
     * 方法执行结束，不管是抛出异常或者正常退出都会执行
     */
    @After(value = "webLog()")
    public void after(JoinPoint joinPoint){
    }

    /**
     * 方法执行结束，增强处理
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret){
    }

    /**
     * 后置异常通知：对异常进行包装，注意异常还是会往上抛出
     */
    @AfterThrowing(value = "webLog()")
    public void throwss(JoinPoint joinPoint){
    }
}
