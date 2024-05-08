package com.tribium.auditking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// https://www.baeldung.com/mongodb-multiple-databases-spring-data
// https://hasangalakdinu.medium.com/how-to-connect-multiple-mongo-db-databases-with-spring-boot-239cb464a114

@SpringBootApplication
@EnableMongoRepositories
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
