package com.tribium.auditking.core.recorder;

import com.tribium.auditking.core.AuditConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecorderFactory {
    @Autowired AuditConfiguration configuration;

    public Recorder getInstance(){
        if(configuration.getRecorderType()== AuditConfiguration.RecorderType.Direct)
            return new DirectRecorder();
        throw new RuntimeException("The other types of recorders are not implemented yet.");
    }
}
