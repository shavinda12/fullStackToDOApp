package com.example.backend.dto;




public class TodoDto {

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


    private long taskId;
    private String title;
    private String description;
    private boolean completed;


    public TodoDto(long taskId, String title, String description, boolean completed) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public TodoDto() {
    }

    //testing purposes
    public TodoDto(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }


}
