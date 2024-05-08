package com.tribium.auditking.core;

import com.tribium.auditking.core.utilities.MapConverter;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
public class AuditAspect {
    @Autowired Auditor auditor;

    @Around("execution(@com.tribium.auditking.core.AuditedMethod * *(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO Register the initial object values...
        List<Object> arguments = Arrays.stream(joinPoint.getArgs()).collect(Collectors.toList());

        System.out.println("method audited: " + joinPoint.getSignature().toString());
        System.out.println("arguments: " + arguments);
        System.out.println("user: " + getUserInfo());

        // Get the information on the object being subject of the auditing operation.
        Annotation annotation = joinPoint.getSignature().getDeclaringType().getAnnotation(AuditedMethod.class);
        if(annotation!=null){
            AuditedMethod a = (AuditedMethod) annotation;
            // TODO
//            a.subjects()
        }
        Object object = (!arguments.isEmpty())?arguments.get(0):null;
        Map<String, Object> oldObjectMap = null;
        if(object!=null)
            oldObjectMap = MapConverter.convertObjectToMap(object);

        // Execute the actual intercepted method...
        Object result = joinPoint.proceed();

        Map<String, Object> newObjectMap = null;
        if(object!=null)
            newObjectMap = MapConverter.convertObjectToMap(object);

        // TODO record the diff
        System.out.println("arguments (updated): " + newObjectMap);

        auditor.recordChanges(object, oldObjectMap, object, newObjectMap, getUserInfo());

        // Return the results.
        return result;
    }

    /**
     * This is the way to get the user information directly from the HTTP request.
     * @return
     */
    private Originator getUserInfo() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = (servletRequestAttributes).getRequest();
        Originator originator = new Originator();
        originator.id = request.getHeader("user");
        return originator;
    }
}
