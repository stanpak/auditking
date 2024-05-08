package com.tribium.auditking.core.storage.mongodb;

import com.tribium.auditking.core.utilities.MapComparator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Document(collection = "change")
public class AuditChange {
    @Id
    public String id;

    public String producerId;
    public String operationId;


    public String objectClass;
    public String objectId;
    public Date time;
    public Map<String, MapComparator.MapDifference> differences;
    public Map<String, Object> oldObject;
    public Map<String, Object> updatedObject;
}
