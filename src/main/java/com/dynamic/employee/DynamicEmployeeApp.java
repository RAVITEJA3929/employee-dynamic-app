package com.dynamic.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.dynamic.employee.entity")
public class DynamicEmployeeApp {
    public static void main(String[] args) {
        SpringApplication.run(DynamicEmployeeApp.class, args);
    }
}
