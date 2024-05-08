package com.tribium.auditking.tryout;

import com.tribium.auditking.core.AuditedMethod;
import com.tribium.auditking.core.AuditedReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @Autowired SampleService sampleService;

    @GetMapping("/doNothing")
    public void doNothing(){}

    /**
     * Method is audited and all the changes to the objects should be surrounded by the context of this method.
     */
    @AuditedMethod
    @GetMapping("/audited")
    public void audited(@AuditedReason String reason){
        // Just use another method to do the lifting...
        notAudited();
    }

    /**
     * This method when invoked should not create the audit entry by itself, although when invoked by audited method -
     * any changes to the data should be recorded within the audit context.
     */
    @GetMapping("/notAudited")
    public void notAudited(){
        // 1. Create an object

        // 2. Update the object

        // 3. Delete the object
    }

}
