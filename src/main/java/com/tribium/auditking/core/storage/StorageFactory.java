package com.tribium.auditking.core.storage;

import com.tribium.auditking.core.AuditConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageFactory {
    @Autowired AuditConfiguration configuration;

    @Autowired ConsoleStorage consoleStorage;

    public Storage getInstance(){
        if(configuration.getStorageType()== AuditConfiguration.StorageType.Console)
            return consoleStorage;
        throw new RuntimeException("No Storage implementation available for this type.");
    }
}
