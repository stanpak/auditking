package com.tribium.auditking.tryout;

import com.tribium.auditking.core.AuditedMethod;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
    @AuditedMethod
    public SampleController.MyObject auditedMethod(SampleController.MyObject obj){
        obj.string = "new value";
        obj.integer = 10;
        return obj;
    }
}
