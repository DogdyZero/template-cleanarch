package com.fiap.techchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Anotação para marcar a classe como ponto de entrada do Spring Boot
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);  // Método para iniciar a aplicação Spring Boot
    }
}
