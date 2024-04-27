package com.tribium.auditking.core;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class AuditAspect {

    @Around("execution(@com.tribium.auditking.core.Audited * *(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO Register the initial object values...
        System.out.println("method audited: " + joinPoint.getSignature().toString());
        System.out.println("arguments: " + Arrays.stream(joinPoint.getArgs()).collect(Collectors.toList()));
        System.out.println("user: " + getUserInfo());

        // Execute the actual intercepted method...
        Object result = joinPoint.proceed();

        // TODO record the diff
        System.out.println("arguments (updated): " + Arrays.stream(joinPoint.getArgs()).collect(Collectors.toList()));

        // Return the results.
        return result;
    }

    /**
     * This is the way to get the user information directly from the HTTP request.
     * @return
     */
    private String getUserInfo() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = (servletRequestAttributes).getRequest();
        return request.getHeader("user");
    }
}
