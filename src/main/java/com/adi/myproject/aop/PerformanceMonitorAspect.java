package com.adi.myproject.aop;

import org.apache.tomcat.websocket.server.WsWriteTimeout;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitorAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitorAspect.class);
    
    @Around("execution(* com.adi.myproject.service.ProductService.*(..))")
    public Object monitorExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();

        LOGGER.info(proceedingJoinPoint.getSignature().getName() + " method executed in " + (endTime-startTime) + "ms");

        return object;
    }

}
