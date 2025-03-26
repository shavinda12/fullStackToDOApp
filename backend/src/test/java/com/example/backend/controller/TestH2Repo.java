package com.example.backend.controller;

import com.example.backend.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TestH2Repo extends JpaRepository<TodoModel,Long> {
}
