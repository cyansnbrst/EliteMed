package com.cyansnbrst.EliteMed;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AOPAspect {
    @Around("allServiceMethods()")
    public Object logParamsAndTime(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("Method: " + joinPoint.getSignature().getName() + " from " + joinPoint.getTarget().getClass() + " executed in " + (end - start) + "ms");
        return result;
    }

    @Pointcut("within(com.cyansnbrst.EliteMed.services.*)")
    public void allServiceMethods() {
    }

}
