package com.jaksim3.bak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BakApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakApplication.class, args);
    }

}
