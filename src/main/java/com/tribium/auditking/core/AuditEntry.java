package com.tribium.auditking.core;

import com.tribium.auditking.core.diff.ObjectDiff;

import java.util.Date;
import java.util.Map;

public class AuditEntry {
    public String objectClass;
    public String objectId;
    public UserInfo user;
    public Date timestamp;
    public ObjectDiff differences;
    public Map<String, Object> oldObject;
    public Map<String, Object> updatedObject;
}
