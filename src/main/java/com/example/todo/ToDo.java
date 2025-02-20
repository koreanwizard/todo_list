package com.example.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


@Entity
public class ToDo {

    private @Id @GeneratedValue Long id;
    private String description;
    private LocalDateTime deadline;

    ToDo() {};

    ToDo(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long newId) {
        this.id = newId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDateTime newDeadline) {
        this.deadline = newDeadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof ToDo)) { return false; }

        ToDo todo = (ToDo) o;
        return Objects.equals(this.id, todo.id)
                && Objects.equals(this.description, todo.description)
                && Objects.equals(this.deadline, todo.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.deadline);
    }

    @Override
    public String toString() {

        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
        String formattedDeadline = deadline.format(formatObj);

        return "ToDo{" + "id = " + this.id
                + ", description = " + this.description
                + ", deadline = " + formattedDeadline
                + "}";
    }
}
