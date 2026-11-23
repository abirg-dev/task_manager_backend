package dev.abirg.task_manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Task title is required")
    @Column(nullable = false)
    private String title;

    @Column(length = 1000) private String description;

    @NotNull(message = "Due date is required")
    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private boolean completed = false;

    public Task() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription() { return description; }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDate getDueDate() { return dueDate; }

    public void setDueDate(LocalDate dueDate)
    {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }
}