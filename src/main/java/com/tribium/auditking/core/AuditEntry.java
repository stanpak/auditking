package com.tribium.auditking.core;

import com.tribium.auditking.core.utilities.MapComparator;

import java.util.Date;
import java.util.Map;

public class AuditEntry {
    public String objectClass;
    public String objectId;
    public UserInfo user;
    public Date timestamp;
    public Map<String, MapComparator.MapDifference> differences;
    public Map<String, Object> oldObject;
    public Map<String, Object> updatedObject;

    @Override
    public String toString() {
        return "AuditEntry{" +
                "objectClass='" + objectClass + '\'' +
                ", objectId='" + objectId + '\'' +
                ", user=" + user +
                ", timestamp=" + timestamp +
                ", differences=" + differences +
                ", oldObject=" + oldObject +
                ", updatedObject=" + updatedObject +
                '}';
    }
}
