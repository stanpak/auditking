package com.tribium.auditking.core.diff;

public class PropertyDiff {
    public Object previousValue;
    public Object newValue;
    public Operation operation;

    public enum Operation{
        Add, Delete, Update
    }
}
