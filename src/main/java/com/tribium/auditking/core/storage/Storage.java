package com.tribium.auditking.core.storage;

import com.tribium.auditking.core.AuditEntry;

public interface Storage {
    void save(AuditEntry entry);
}
