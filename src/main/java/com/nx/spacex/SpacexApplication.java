package com.nx.spacex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.nx.spacex.entity")
public class SpacexApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpacexApplication.class, args);
    }

}
