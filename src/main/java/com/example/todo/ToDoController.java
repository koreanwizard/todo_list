package com.example.todo;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class ToDoController {

    private final ToDoRepository repository;

    private final ToDoModelAssembler assembler;

    ToDoController(ToDoRepository repository, ToDoModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }




    @GetMapping("/todos")
    CollectionModel<EntityModel<ToDo>> getEveryToDo() {
        /*
        A container that encapsulates collections of ToDo's resources (also includes links).

        The assembler replaces the complex logic of EntityModel<Employee> creation by map(assembler::toModel)

        -> stream every ToDo item from the repository, apply the 'toModel()' (method from ToDoAssembler class) to every
        ToDo item.
         */

        List<EntityModel<ToDo>> todos = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

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

        return assembler.toModel(todo);
    }


    @PostMapping("/todos/new")
    ResponseEntity<?> newEmployee(@RequestBody ToDo newToDo) {
        /*
        new ToDo item is saved in the repository by the ToDoModelAssembler wrapper.
        creates an HTTP 201 Created status message (including location response header)

        THIS SHOULD BE FIXED BECAUSE THE DEADLINE ATTRIBUTE IS A LocalDateTime CLASS.
        I TRIED TO CONVERT IT TO JSON TO USE ON POST REQUEST (BY USING CURL COMMAND).
        IT'S NOT WORKING.
         */

        EntityModel<ToDo> entityModel = assembler.toModel(repository.save(newToDo));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/todos/replace/{id}")
    ResponseEntity<?> replaceToDo(@RequestBody ToDo newToDo, @PathVariable Long id) {
        /*
        update a certain ToDo item (accessing by its id)
        creates an HTTP 201 but not suitable (because we're not CREATING  a new ToDo; we're just updating)

        THIS SHOULD ALSO BE FIXED AND HAS A SAME PROBLEM AS THE newEmployee METHOD.
         */
        ToDo updatedToDo = repository.findById(id).map(todo -> {
            todo.setDescription(newToDo.getDescription());
            todo.setDeadline(newToDo.getDeadline());
            todo.setStar(newToDo.getStar());
            return repository.save(todo);
        }).orElseGet(() -> {
            return repository.save(newToDo);
        });

        EntityModel<ToDo> entityModel = assembler.toModel(updatedToDo);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }


    @DeleteMapping("/todos/{id}")
    ResponseEntity<?> deleteToDoById(@PathVariable Long id) {
        /*
        delete a certain ToDo item by accessing the item's id.
        returns an HTTP 204 No Content response.
         */
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

