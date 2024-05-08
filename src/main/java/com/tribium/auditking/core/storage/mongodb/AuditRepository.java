package com.tribium.auditking.core.storage.mongodb;

import com.tribium.auditking.core.AuditEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<AuditEntry, String> {
}
