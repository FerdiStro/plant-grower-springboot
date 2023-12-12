package com.plantgrowerspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(scanBasePackages = "com.plantgrowerspringboot")
public class PlantGrowerSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlantGrowerSpringbootApplication.class, args);
    }

}