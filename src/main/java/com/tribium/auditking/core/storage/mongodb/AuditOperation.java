package com.tribium.auditking.core.storage.mongodb;

import com.tribium.auditking.core.AuditEntry;
import com.tribium.auditking.core.Originator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "operation")
public class AuditOperation {
    @Id
    public String id;

    /**
     * ID of the system that owns and manages the business object data that is submitting the auditing information.
     */
    public String producerId;
    public Date startTime;
    public Date endTime;
    public Originator originator;

    /**
     * Any details about the business operation within which the changes have occurred.
     */
    public AuditEntry.Operation operation;
}
