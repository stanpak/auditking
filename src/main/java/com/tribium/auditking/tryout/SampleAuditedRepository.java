package com.tribium.auditking.tryout;

import com.tribium.auditking.core.AuditedRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

@AuditedRepository
public interface SampleAuditedRepository extends MongoRepository<SampleAuditedObject,String> {
}
