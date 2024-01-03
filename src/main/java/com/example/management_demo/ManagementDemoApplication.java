package com.example.management_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ManagementDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementDemoApplication.class, args);
    }

}
