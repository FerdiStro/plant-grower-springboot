package com.plantgrowerspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.plantgrowerspringboot")
@EnableScheduling
public class PlantGrowerSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlantGrowerSpringbootApplication.class, args);
    }

}