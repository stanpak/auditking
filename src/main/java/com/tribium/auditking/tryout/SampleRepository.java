package com.tribium.auditking.tryout;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SampleRepository extends MongoRepository<SampleObject,String> {
}
