package com.ibm.w3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ImportDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImportDataApplication.class, args);
    }

}
