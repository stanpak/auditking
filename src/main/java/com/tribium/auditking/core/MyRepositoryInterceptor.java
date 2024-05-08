package com.tribium.auditking.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyRepositoryInterceptor {
        @Around("execution(* *(..)) && @within(AuditedRepository)")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
            // Your interception logic goes here
            System.out.println("Intercepting repository method #1...");
            Object result =  joinPoint.proceed();
            System.out.println("Intercepting repository method #2...");

            return result;
        }
}
