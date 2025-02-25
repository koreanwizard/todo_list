package com.example.todo;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class ToDoController {

    private final ToDoRepository repository;

    ToDoController(ToDoRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/todos")
    CollectionModel<EntityModel<ToDo>> getEveryToDo() {

        List<EntityModel<ToDo>> todos = repository.findAll().stream().map(todo -> EntityModel.of(todo,
                linkTo(methodOn(ToDoController.class).getSingleToDoByID(todo.getId())).withSelfRel(),
                linkTo(methodOn(ToDoController.class).getEveryToDo()).withRel("/todos"))).collect(Collectors.toList());

        return CollectionModel.of(todos, linkTo(methodOn(ToDoController.class).getEveryToDo()).withSelfRel());
    }




    @GetMapping("/todos/{id}")
    EntityModel<ToDo> getSingleToDoByID(@PathVariable Long id) {
        /*
        A generic container EntityModel<T> is provided by Spring HATEOAS.
        It contains the data and a collection of links.

        Build a link for getSingleToDoByID with a 'self' flag.
        Build a link for getEveryToDo with a 'todos.'
         */

        ToDo todo = repository.findById(id).orElseThrow(() -> new ToDoNotFoundException(id));

        System.out.println(todo);

        return EntityModel.of(todo,
                linkTo(methodOn(ToDoController.class).getSingleToDoByID(id)).withSelfRel(),
                linkTo(methodOn(ToDoController.class).getEveryToDo()).withRel("todos"));
    }

    @GetMapping("/todos/isStar/{star}")
    EntityModel<ToDo> getStarToDoByStar(@PathVariable boolean star) {
        ToDo todo = repository.findByStarTrue(star);


        return EntityModel.of(todo,
                linkTo(methodOn(ToDoController.class).getStarToDoByStar(star)).withSelfRel());
    }


//    @GetMapping("/todos/isStar/{star}")
//    CollectionModel<EntityModel<ToDo>> getEveryToDoWithStar(@PathVariable boolean star) {
//
//        List<EntityModel<ToDo>> todos = repository.findByStarTrue(star);
//
//        return CollectionModel.of(todos, linkTo(methodOn(ToDoController.class).getEveryToDoWithStar(star)).withSelfRel());
//    }


}

