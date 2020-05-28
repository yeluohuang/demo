package com.example.demo.aop;

import com.example.demo.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * 执行顺序： around-before-method-around-after/afterThrowing
 * @description 面向切面的配置：记录系统日志，存储于数据库中
 * @author zhushj3
 * @date 2020/04/28
 */
@Aspect
@Component
public class LogAspect {
    /**
     * slf4j日志
     */
    private final static Logger logger = LoggerFactory.getLogger("test");

    /**
     * Pointcut 切入点,即需要执行切面编程的类或者函数
     * 匹配日志注解
     */
    @Pointcut("@annotation(com.example.demo.annotation.declare.Log)")
    public void webLog(){}

    /**
     * 环绕通知：在连接点前进行操作:此处可以进行全局异常处理，或者交由全局异常处理器处理
     * 类似于过滤器
     */
    @Around(value = "webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取切点的对象信息
//        MethodSignature signature = (MethodSignature) pjp.getSignature();
//        Method method = signature.getMethod();
//        Log log = method.getAnnotation(Log.class);
//        String msg = log.value();
       // logger.info("测试Log注解"+msg);
        return pjp.proceed();
    }

    /**
     * 函数执行前，around之后
     */
    @Before(value = "webLog()")
    public void before(JoinPoint joinPoint){
        LogUtil.addValue(Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 连接点退出（结束或者异常退出）
     */
    @After(value = "webLog()")
    public void after(JoinPoint joinPoint){
    }

    /**
     * 方法执行结束，增强处理
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret){
        LogUtil.addValue(ret.toString());
        logger.info(LogUtil.getValue());
        LogUtil.clearValue();
    }

    /**
     * 后置异常通知：对异常进行包装，注意异常还是会往上抛出
     */
    @AfterThrowing(value = "webLog()")
    public void throwss(JoinPoint joinPoint){
        LogUtil.clearValue();
    }
}
