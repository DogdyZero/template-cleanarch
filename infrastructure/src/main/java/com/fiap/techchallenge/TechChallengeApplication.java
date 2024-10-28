package com.fiap.techchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.fiap.techchallenge.persistence.repositories")
@EntityScan("com.fiap.techchallenge.persistence.entities")
@EnableScheduling
public class TechChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TechChallengeApplication.class, args);
    }
}
