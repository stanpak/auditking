package com.tribium.auditking.tryout;

import com.tribium.auditking.core.Audited;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
    @Audited
    public SampleController.MyObject auditedMethod(SampleController.MyObject obj){
        obj.string = "new value";
        obj.integer = 10;
        return obj;
    }
}
