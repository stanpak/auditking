package com.tribium.auditking.core;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyRepositoryInterceptor {
        @Around("execution(* *(..)) && @annotation(Audited)")
        public void beforeRepositoryMethodExecution() {
            // Your interception logic goes here
            System.out.println("Intercepting repository method...");
        }
}
