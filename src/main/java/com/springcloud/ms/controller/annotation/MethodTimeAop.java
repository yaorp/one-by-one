package com.springcloud.ms.controller.annotation;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author: yaorp
 */
@Aspect
@Component
@Slf4j
public class MethodTimeAop {

    @Around("@annotation(com.springcloud.ms.controller.annotation.MethodTime)")
    public Object methodTime(ProceedingJoinPoint pjp) {
        log.info("方法执行时间：{}", System.currentTimeMillis());

        Object proceed = null;
        try {
            Object[] args = pjp.getArgs();
            log.info("方法参数：{}", JSONObject.toJSONString(args));
            proceed = pjp.proceed();
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(proceed);

            log.info("方法返回值：{}", s);
            log.info("方法执行时间：{}", System.currentTimeMillis());
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }



        return proceed;

    }
}
