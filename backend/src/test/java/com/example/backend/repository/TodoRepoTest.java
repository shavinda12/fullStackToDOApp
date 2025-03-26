package com.example.backend.repository;

import com.example.backend.model.TodoModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataJpaTest
class TodoRepoTest {

    @Autowired
    private TodoRepo underTest;

    @Test
    void itShouldCheckTop05IncompletedTasks() {
        //given
        List<TodoModel> todos = Arrays.asList(
                new TodoModel("A","Description A",false),
                new TodoModel("B","Description B",false),
                new TodoModel("C","Description C",false),
                new TodoModel("D","Description D",true),
                new TodoModel("F","Description F",false),
                new TodoModel("G","Description G",true),
                new TodoModel("H","Description H",false)
        );
        underTest.saveAll(todos);

        //when
        List<TodoModel> taskList=underTest.getTodoList();

        //then
        assertThat(taskList).hasSize(5);
        assertThat(taskList.get(0).getTitle()).isEqualTo("H");
        assertThat(taskList.get(3).getTitle()).isEqualTo("B");
    }
}