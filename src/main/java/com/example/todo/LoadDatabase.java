package com.example.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ToDoRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new ToDo("end my military service",
                    LocalDateTime.of(2025, 3, 24, 8, 30))));
            log.info("Preloading " + repository.save(new ToDo("extend my F1 VISA",
                    LocalDateTime.of(2025, 6, 1, 0, 0))));
        };
    }
}
