package com.example.backend.service;

import com.example.backend.dto.TodoDto;
import com.example.backend.model.TodoModel;
import com.example.backend.repository.TodoRepo;
import com.example.backend.response.Response;
import com.example.backend.response.SuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        when(modelMapper.map(todoDto, TodoModel.class)).thenReturn(todoModel);  // Mock mapping
        when(todoRepo.save(todoModel)).thenReturn(savedTodoModel);  // Mock saving in repository
        when(modelMapper.map(savedTodoModel, TodoDto.class)).thenReturn(todoDto);  // Mock mapping back to TodoDto

        ResponseEntity<Response> response = underTest.saveTodo(todoDto);  // Call the saveTodo method

        // Then
        verify(todoRepo).save(todoModel);  // Verify that the save method was called on the repository
        verify(modelMapper).map(todoDto, TodoModel.class);  // Verify that map was called on the modelMapper

        // Assert that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert that the response body is of type SuccessResponse and contains the correct TodoDto
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse<TodoDto> successResponse = (SuccessResponse<TodoDto>) response.getBody();
        assertEquals(todoDto, successResponse.getData());  // Assert the saved TodoDto is returned

    }

    @Test
    @Disabled
    void deleteTodo() {
    }
}