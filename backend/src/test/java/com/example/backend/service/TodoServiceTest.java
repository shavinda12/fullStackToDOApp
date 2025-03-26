package com.example.backend.service;

import com.example.backend.dto.TodoDto;
import com.example.backend.model.TodoModel;
import com.example.backend.repository.TodoRepo;
import com.example.backend.response.Response;
import com.example.backend.response.SuccessResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepo todoRepo;

    @Mock
    private ModelMapper modelMapper;

    private TodoService underTest;

    @BeforeEach
    void setUp() {
        underTest=new TodoService(todoRepo,modelMapper);
    }

    @AfterEach
    void tearDown() {
        todoRepo.deleteAll();
    }

    @Test
    void canGetFirstFiveTodos() {
        //when
        underTest.getFirstFiveTodos();

        //then
        verify(todoRepo).getTodoList();
    }

    @Test
    void canSaveTodo() {

        // Given
        TodoDto todoDto = new TodoDto("Task A", "Description A", false);  // Sample TodoDto
        TodoModel todoModel = new TodoModel("Task A", "Description A", false);  // Corresponding TodoModel
        TodoModel savedTodoModel = new TodoModel(1, "Task A", "Description A", true);  // Saved TodoModel with an ID

        // When
        when(modelMapper.map(todoDto, TodoModel.class)).thenReturn(todoModel);
        when(todoRepo.save(todoModel)).thenReturn(savedTodoModel);
        when(modelMapper.map(savedTodoModel, TodoDto.class)).thenReturn(todoDto);


        ResponseEntity<Response> response = underTest.saveTodo(todoDto);

        // Then
        verify(todoRepo).save(todoModel);
        verify(modelMapper).map(todoDto, TodoModel.class);


        assertEquals(HttpStatus.OK, response.getStatusCode());


        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse<TodoDto> successResponse = (SuccessResponse<TodoDto>) response.getBody();
        assertEquals(todoDto, successResponse.getData());

    }

    @Test
    void canDeleteTodoSuccessfully() {
        // Given
        long taskId = 1L;
        when(todoRepo.updateIsCompletedToTrue(taskId)).thenReturn(1);  // Simulate successful update

        // When
        ResponseEntity<Response> response = underTest.deleteTodo(taskId);

        // Then
        verify(todoRepo).updateIsCompletedToTrue(taskId);  // Verify that updateIsCompletedToTrue was called
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void cannotDeleteTodoWhenTaskNotFound() {
        // Given
        long taskId = 1L;
        when(todoRepo.updateIsCompletedToTrue(taskId)).thenReturn(0);  // Simulate task not found (not updated)

        // When
        ResponseEntity<Response> response = underTest.deleteTodo(taskId);

        // Then
        verify(todoRepo).updateIsCompletedToTrue(taskId);  // Verify that updateIsCompletedToTrue was called
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());  // Assert the status is NOT_FOUND
    }


}