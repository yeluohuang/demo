package com.example.demo.aspect;

import com.example.demo.exception.BaseException;
import com.example.demo.exception.TestException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/** 面向切面的配置：打印应用日志
 * @author zhushj3
 * @date 2020/04/28
 */
@Aspect
@Component
public class WebLogAspect {
    /**
     * slf4j日志
     */
    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * Pointcut 切入点,即需要执行切面编程的类或者函数
     * 匹配com.example.demo包下面的所有方法
     */
    @Pointcut("execution(public * com.example..controller.*.*(..))")
    public void webLog(){}

    /**
     * 环绕通知：在执行函数的过程中进行操作:此处可以进行全局异常处理，或者交由全局异常处理器处理
     */
    @Around(value = "webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object o=null;
        try {
            o = pjp.proceed();
        } catch (Exception exception){
            // 判断异常类型
            if(exception instanceof TestException){
                throw new TestException((BaseException) exception);
            } else {
                throw  exception;
            }
        } finally {
            long end = System.currentTimeMillis();
            long duration = end -start;
            logger.info("耗时："+String.valueOf(duration)+"ms");
        }
        return o;
    }

    /**
     * 方法执行前
     */
    @Before(value = "webLog()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 打印请求信息
        logger.info("requestURL : " + request.getMethod()+ "?"+request.getRequestURL().toString());
        logger.info("clientIP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求参数 : " + Arrays.toString(joinPoint.getArgs())); // 直接取的方法内的参数，非传递过来的数据

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
        // 处理完请求，返回内容
        logger.info("返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing(value = "webLog()")
    public void throwss(JoinPoint joinPoint){
    }
}
