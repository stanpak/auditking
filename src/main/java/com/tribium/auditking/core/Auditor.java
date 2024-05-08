package com.tribium.auditking.core;

import com.tribium.auditking.core.utilities.MapComparator;
import com.tribium.auditking.core.recorder.RecorderFactory;
import com.tribium.auditking.core.recorder.RecorderTask;
import com.tribium.auditking.core.storage.StorageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Main class used to interact with AuditKing from the business code. It has couple simple yet useful methods
 * to record changes on objects or to get the reports from the auditing database.
 */
@Component
public class Auditor {
    @Autowired
    RecorderFactory recorderFactory;
    @Autowired
    StorageFactory storageFactory;
    @Autowired
    AuditConfiguration configuration;

    public void recordChanges(Object oldObject, Map<String, Object>  oldObjectMap,
                              Object updatedObject, Map<String, Object>  updatedObjectMap,
                              Originator originator) {
        //  Depending on the mode of operation it can spawn a thread or send a message to the queue or just execute
        //  the code immediately. The method is delegated to the Recorder implementation.
        RecorderTask recorderTask = () -> {
            //  In the first step the both objects are analyzed and any differences in values are identified.
            //  The analysis is done using reflection.
            //  The results are stored in the map representing the differences.
            //  Depending on the options the changes can be saved as a diff or as both object values.
            //  If the latter then the objects are converted to the maps.
            Map<String, MapComparator.MapDifference> diff = MapComparator.compareMaps(oldObjectMap, updatedObjectMap);

            // New record in the database is created with additional timestamp and user information.
            AuditEntry entry = new AuditEntry();
//            entry.differences = diff;
//            entry.originator = originator;
//            entry.timestamp = new Date();
//
//            // Save the metadata about the object...
//            if (oldObject != null) {
//                entry.objectClass = oldObject.getClass().getName();
//                entry.objectId = getObjectId(oldObject);
//            } else if (updatedObject != null) {
//                entry.objectClass = updatedObject.getClass().getName();
//                entry.objectId = getObjectId(updatedObject);
//            } else throw new RuntimeException("Impossible to get object information.");
//
//            // Store the object values if that was required...
//            if (configuration.isRecordFullObjectDetails()) {
//                if (oldObject != null)
//                    entry.oldObject = oldObjectMap;
//                if (updatedObject != null)
//                    entry.updatedObject = updatedObjectMap;
//            }

            // Save the entry into the proper storage location.
            storageFactory.getInstance().save(entry);
        };
        recorderFactory.getInstance().record(recorderTask);
    }

    // TODO
    private String getObjectId(Object oldObject) {
        return null;
    }
}
