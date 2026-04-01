package com.springcloud.ms.aspect;

import cn.hutool.core.date.StopWatch;
import com.springcloud.ms.annotation.LogExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 记录方法执行时间的切面
 * @author yaorp
 */
@Slf4j
@Aspect
@Component
public class LogExecutionTimeAspect {

    @Around("@annotation(com.springcloud.ms.annotation.LogExecutionTime)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("方法开始执行");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        MethodSignature ms = (MethodSignature)pjp.getSignature();
        Method method = ms.getMethod();
        LogExecutionTime annotation = method.getAnnotation(LogExecutionTime.class);
        Object proceed = pjp.proceed();
        stopWatch.stop();
        log.info("方法执行结束:方法名称:{},方法中文名称:{},执行时间: {} 秒", annotation.value(), annotation.name(), stopWatch.getTotalTimeSeconds());
        return proceed;
    }
}
