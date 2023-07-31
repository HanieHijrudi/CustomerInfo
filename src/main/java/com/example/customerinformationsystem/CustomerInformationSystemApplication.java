package com.example.customerinformationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerInformationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerInformationSystemApplication.class, args);
    }

    @Bean
    public ProgressTracker progressTracker() {
        return new ProgressTracker();
    }
}
