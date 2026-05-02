package com.example.bookauthor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application class.
 * @SpringBootApplication is a convenience annotation that combines:
 *   @Configuration, @EnableAutoConfiguration, @ComponentScan
 */
@SpringBootApplication
public class BookAuthorApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookAuthorApplication.class, args);
    }
}
