package com.example.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query("SELECT todo FROM Todo todo WHERE todo.star = true")
    ToDo findByStarTrue(boolean star);
}
