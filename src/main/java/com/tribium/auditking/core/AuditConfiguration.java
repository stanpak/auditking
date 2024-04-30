package com.tribium.auditking.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("audit")
@Configuration
public class AuditConfiguration {
    public RecorderType getRecorderType() {
        return recorderType;
    }

    public void setRecorderType(RecorderType recorderType) {
        this.recorderType = recorderType;
    }

    private RecorderType recorderType = RecorderType.Direct;

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    private StorageType storageType = StorageType.Console;

    public enum  RecorderType{
        Direct, Thread, MQ
    }

    public enum  StorageType{
        Console, MongoDB
    }

    public boolean isRecordFullObjectDetails() {
        return recordFullObjectDetails;
    }

    public void setRecordFullObjectDetails(boolean recordFullObjectDetails) {
        this.recordFullObjectDetails = recordFullObjectDetails;
    }

    private boolean recordFullObjectDetails = false;
}
