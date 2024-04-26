package com.tribium.auditking.core.storage;

import com.tribium.auditking.core.AuditConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageFactory {
    @Autowired AuditConfiguration configuration;

    public Storage getInstance(){
        // TODO
        return null;
    }
}
