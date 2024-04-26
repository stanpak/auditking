package com.tribium.auditking.core.storage;

import com.tribium.auditking.core.AuditEntry;
import com.tribium.auditking.core.UserInfo;
import com.tribium.auditking.core.diff.ObjectDiff;

public interface Storage {
    void save(AuditEntry entry);
}
