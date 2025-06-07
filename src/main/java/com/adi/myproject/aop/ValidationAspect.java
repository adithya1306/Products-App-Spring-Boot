package com.adi.myproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.adi.myproject.service.ProductService.*(..)) && args(id)")
    public Object validateInput(ProceedingJoinPoint proceedingJoinPoint, int id) throws Throwable {
        if (id < 0) {
            LOGGER.info("Updating neg parameter value as positive");
            id = -id;
        }

        Object object = proceedingJoinPoint.proceed(new Object[]{id});

        return object;
    }
}
