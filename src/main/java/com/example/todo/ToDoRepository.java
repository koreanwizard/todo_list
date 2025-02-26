package com.example.todo;

import org.springframework.data.jpa.repository.JpaRepository;

interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
