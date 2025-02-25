package com.example.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Objects;


@Entity  // JPA annotation to create and use this object as a storage of JPA-based data.
class ToDo {
    /*
    id: a primary key and automatically created by the JPA provider
    description: a description of a single To-Do (a task that user should do)
    deadline: a deadline of a single To-Do (the LocalDateTime class includes certain date)
     */

    private @Id @GeneratedValue Long id;
    private String description;
    private boolean star;
    private LocalDateTime deadline;

    ToDo() {}; // a default constructor

    ToDo(String description, boolean star, LocalDateTime deadline) {
        /*
        contains two parameters which is the description and the deadline (date) of a single to-do item.
         */

        this.description = description;
        this.star = star;
        this.deadline = deadline;
    }

    /*
    Getters and Setters to access every attribute from a single to-do item.
     */
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

    public boolean getStar() {
        return this.star;
    }

    public void setStar(boolean newStar) {
        this.star = newStar;
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

        ToDo todo = (ToDo) o; // We need to cast the Object o to compare every attribute.
        return Objects.equals(this.id, todo.id)
                && Objects.equals(this.description, todo.description)
                && Objects.equals(this.star, todo.star)
                && Objects.equals(this.deadline, todo.deadline);
    }

    @Override
    public int hashCode() {
        /*
         also need to be overridden to avoid containing a different hashcode from same objects.
         */
        return Objects.hash(this.id, this.description, this.star, this.deadline);
    }

    @Override
    public String toString() {
        /*
        The DateTimeFormatter.ofPattern method is a formatter that contains specific pattern
        ex: Friday, APRIL 4th 2025 13:20:00
        The format method is a formatter from LocalDateTime class. The parameter is a DateTimeFormatter object.
         */

        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
        String formattedDeadline = deadline.format(formatObj);

        return "ToDo{" + "id = " + this.id
                + ", description = " + this.description
                + ", star = " + this.star
                + ", deadline = " + formattedDeadline
                + "}";
    }
}
