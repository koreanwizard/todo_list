package com.example.todo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ToDoModelAssembler implements RepresentationModelAssembler<ToDo, EntityModel<ToDo>>{

    @Override
    public EntityModel<ToDo> toModel(ToDo todo) {
        /*
        convert a non-model object(ToDo) into a model-based object (EntityModel<ToDo>)

         */

        return EntityModel.of(todo,
                linkTo(methodOn(ToDoController.class).getSingleToDoByID(todo.getId())).withSelfRel(),
                linkTo(methodOn(ToDoController.class).getEveryToDo()).withRel("todos"));
    }


}
