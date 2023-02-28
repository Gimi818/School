package com.example.schoolproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@ConfigurationPropertiesScan
public class SchoolProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolProjectApplication.class, args);
    }

}
