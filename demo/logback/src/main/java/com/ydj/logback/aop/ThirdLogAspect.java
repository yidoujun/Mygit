package com.ydj.logback.aop;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

@Slf4j
@Component
@Aspect
public class ThirdLogAspect {

    private Gson gson = new Gson();

    @Around("@annotation(com.ydj.logback.aop.ThirdPart)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        Parameter[] parameters = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
        Object[] args = joinPoint.getArgs();

        StringBuilder params = new StringBuilder("[");
        for (int i = 0; i < parameters.length; i++) {
            params.append(parameters[i].getName()).append(":").append(args[i]).append("; ");
        }
        if (parameters.length > 0) {
            int length = params.length();
            params.delete(length - 2, length);
        }
        params.append("]");

        log.info(">>>> Target class name={} | method name={} | params={} <<<<",className,methodName,params);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error(">>>> Target class name={} | method name={}\n{}\n\t at {} <<<<",className, methodName, e.toString(), e.getStackTrace()[0]);
            throw e;
        }
        log.info(">>>> Target class name={} | method name={} | results={} <<<<",className, methodName, gson.toJson(result));
        return result;
    }
}
