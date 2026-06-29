package com.mehraj.amazonlite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AmazonLiteBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonLiteBackendApplication.class, args);
    }

}