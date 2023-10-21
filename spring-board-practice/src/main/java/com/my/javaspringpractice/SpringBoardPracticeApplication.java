package com.my.javaspringpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@ConfigurationPropertiesScan
@SpringBootApplication
public class SpringBoardPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoardPracticeApplication.class, args);

    }
}