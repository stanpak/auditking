# auditking
Small yet powerful audit trail recording library based on Java and MongoDB

## Introduction

- Business logic code + business data
- Aspects which are orthogonal to the busieness logic:
  - Authentication
  - Authorization
    - function based
    - object instance based
  - Event logging
  - Error handling
  - Audit trail recording
- Primary purpose of audit trail recording:
  - Recording changes in the important data objects
  - When they happened
  - Nature of the operation
  - Who did the changes
  - What was changed specifically
 
## Features

- Asynchronic processing
  When the data needs to be saved into the audit database, we want it to me as performant as possible. Therefore any function like this should be in principle asynchronic (either sending the data to the queue for further processing or spawning new detached thread). This feature should make the code very performant and have minor effect of on the overall business logic performance.
- No changes to the business data structures.
- No additional code needed to be added to the business logic.
- Very simple data extraction from the DB for reporting purposes.
- Storing whole history of object changes
- No need to modify the core business data structures
- The audit database can be separate from the business data
- The retention can be added and managed separately from the business data retention.

## Sample Java code

### Recording the change:
In order to record the change of the object there are following things needed:
 - old object
 - new (modified object)
 - user information
```Java
Auditor.recordChange(object, updatedObject, userInfo);
```
   
Or, alternatively:
 - new (modified object)
 - object repository
 - user information
```Java
Auditor.recordChange(object, objectRepository, userInfo);
```

All of these operations should be made prior saving updated object in the database using the repository. 

