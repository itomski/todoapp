package de.lubowiecki.todos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Entity // Soll in der DB verwaltet werden
public class Todo {

    @Id // Primärschlüssel
    @GeneratedValue // Autoincrement
    private int id;

    @Column(length = 100)
    private String title;

    // @JsonIgnore // Wird bei einer Json-Anzeigen ignoriert
    private String description;

    private LocalDateTime createdAt;

    private boolean done;

    public Todo() {
    }

    public Todo(String title, String description, LocalDateTime createdAt, boolean done) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.done = done;
    }

    public Todo(String title, String description) {
        this(title, description, LocalDateTime.now(), false);
    }

    public Todo(String title) {
        this(title, "", LocalDateTime.now(), false);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getLocalCreatedAt() {
        final DateTimeFormatter FMT = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return createdAt.format(FMT);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
