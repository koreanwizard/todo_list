package com.example.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.EntityModel;

import java.util.List;



interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query(value = "SELECT t FROM Todo t WHERE t.star = true", nativeQuery = true)
    List<ToDo> findByStarTrue(@Param("star") boolean star);
}
