package com.example.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ToDoController {

    private final ToDoRepository repository;

    ToDoController(ToDoRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/todos")
    List<ToDo> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

}

