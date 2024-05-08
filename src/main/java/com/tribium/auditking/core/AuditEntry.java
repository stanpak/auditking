package com.tribium.auditking.core;

import com.tribium.auditking.core.utilities.MapComparator;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuditEntry {
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
    public Operation operation;

    /**
     * All changes to the business objects that were done within this operation grouped by the object class.
     */
    public Map<String, List<ObjectChange>> changes = new HashMap<>();

    /**
     * Class describing and keeping the business operation context to large detail.
     */
    public static class Operation{
        // class/method/file/line etc.
        // parameters
        // final results
        // stack trace?
    }

    /**
     * Structure describing singular change to the business object.
     */
    public static class ObjectChange{
        public String objectClass;
        public String objectId;
        public Date time;
        public Map<String, MapComparator.MapDifference> differences;
        public Map<String, Object> oldObject;
        public Map<String, Object> updatedObject;
    }
}
