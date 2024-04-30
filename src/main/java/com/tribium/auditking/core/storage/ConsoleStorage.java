package com.tribium.auditking.core.storage;

import com.tribium.auditking.core.AuditEntry;
import org.springframework.stereotype.Component;

@Component
public class ConsoleStorage implements Storage {
    @Override
    public void save(AuditEntry entry) {
        System.out.println(entry);
    }
}
