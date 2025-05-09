package com.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="task")
public class TodoModel {

    public long getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;
    private String title;
    private String description;
    private boolean completed;

    //this is for testing purposes
    public TodoModel(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }
    public TodoModel(long taskId, String title, String description, boolean completed) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public TodoModel() {
    }

}
