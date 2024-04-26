package com.tribium.auditking.core;

import com.tribium.auditking.core.diff.DifferenceAnalyzer;
import com.tribium.auditking.core.diff.ObjectDiff;
import com.tribium.auditking.core.recorder.RecorderFactory;
import com.tribium.auditking.core.storage.StorageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Main class used to interact with AuditKing from the business code. It has couple simple yet useful methods
 * to record changes on objects or to get the reports from the auditing database.
 */
@Component
public class Auditor {
    @Autowired RecorderFactory recorderFactory;
    @Autowired StorageFactory storageFactory;
    @Autowired AuditConfiguration configuration;

    public void recordChanges(Object oldObject, Object updatedObject, UserInfo userInfo){
        // TODO Depending on the mode of operation it can spawn a thread or send a message to the queue or just execute
        //  the code immediately.
        recorderFactory.getInstance().record(()->{
            // TODO In the first step the both objects are analyzed and any differences in values are identified.
            //  The analysis is done using reflection.
            //  The results are stored in the map representing the differences.
            DifferenceAnalyzer da = new DifferenceAnalyzer();
            ObjectDiff diff = da.getDiff(oldObject, updatedObject);

            // TODO Depending on the options the changes can be saved as a diff or as both object values.
            //  If the latter then the objects are converted to the maps.
            AuditEntry entry = new AuditEntry();
            entry.differences = diff;
            entry.user = userInfo;
            entry.timestamp = new Date();
            if(oldObject!=null){
                entry.objectClass =  oldObject.getClass().getName();
                entry.objectId =  getObjectId(oldObject);
            }else if(updatedObject!=null){
                entry.objectClass =  updatedObject.getClass().getName();
                entry.objectId =  getObjectId(updatedObject);
            }
            if(configuration.isRecordFullObjectDetails()){
                entry.oldObject = DifferenceAnalyzer.objectToMap(oldObject);
                entry.updatedObject = DifferenceAnalyzer.objectToMap(updatedObject);
            }
            else throw new RuntimeException("Impossible to get object information.");

            // TODO New record in the database is created with additional timestamp and user information.
            storageFactory.getInstance().save(entry);
        });
    }

    // TODO
    private String getObjectId(Object oldObject) {
        return null;
    }
}
