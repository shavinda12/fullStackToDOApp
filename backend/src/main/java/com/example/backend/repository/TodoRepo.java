package com.example.backend.repository;

import com.example.backend.dto.TodoDto;
import com.example.backend.model.TodoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepo extends JpaRepository<TodoModel,Long> {

    @Query(value = "SELECT * FROM task WHERE completed=false order by task_id DESC LIMIT 5",nativeQuery = true)
    List<TodoModel> getTodoList();

    @Modifying
    @Transactional
    @Query("UPDATE TodoModel t SET t.completed = true WHERE t.taskId = :taskId")
    int updateIsCompletedToTrue(Long taskId);

}
