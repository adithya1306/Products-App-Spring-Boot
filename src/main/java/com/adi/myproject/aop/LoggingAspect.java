package com.adi.myproject.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // return type, class-name.method-name(arguments)

    @Before("execution(* com.adi.myproject.service.ProductService.*(..))")
    public void logMethodCallBefore(JoinPoint jp){
        LOGGER.info("Method " + jp.getSignature().getName() + " called");
    }


    @After("execution(* com.adi.myproject.service.ProductService.*(..))")
    public void logMethodCallAfter(JoinPoint jp){
        LOGGER.info("Method " + jp.getSignature().getName() + " executed");
    }

    @AfterThrowing("execution(* com.adi.myproject.service.ProductService.*(..))")
    public void logMethodCallAfterThrowing(JoinPoint jp){
        LOGGER.info("Method " + jp.getSignature().getName() + " threw error");
    }

    @AfterReturning("execution(* com.adi.myproject.service.ProductService.*(..))")
    public void logMethodCallAfterReturning(JoinPoint jp){
        LOGGER.info("Method " + jp.getSignature().getName() + " returned");
    }
}
