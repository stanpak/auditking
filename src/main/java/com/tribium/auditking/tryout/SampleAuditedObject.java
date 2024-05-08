package com.tribium.auditking.tryout;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class SampleAuditedObject {
    @Id
    public String id;

    public String name;
    public int age;
    public Date birthDate;
}
