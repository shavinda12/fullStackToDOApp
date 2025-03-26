package com.example.backend.controller;

import com.example.backend.dto.TodoDto;
import com.example.backend.model.TodoModel;
import com.example.backend.response.Response;
import com.example.backend.response.SuccessResponse;
import com.example.backend.service.TodoService;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@TestPropertySource(properties = "spring.config.location=classpath:application-test.properties")
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;  // Mock your service

    @Autowired
    private ObjectMapper objectMapper;// Used for converting objects to JSON

    @Autowired
    private TestH2Repo testH2Repo;

    private TodoDto todoDto;

    @BeforeEach
    public void setUp() {
        // Set up the test data
        todoDto = new TodoDto();
        todoDto.setTaskId(1);
        todoDto.setTitle("Test Todo");
        todoDto.setDescription("Test Description");
        todoDto.setCompleted(false);

    }


    @Test
    void postTodo() throws Exception {

        // Mocking the service layer response
        SuccessResponse<TodoDto> successResponse = new SuccessResponse<>(todoDto);
        ResponseEntity<Response> mockResponse = ResponseEntity.ok(successResponse);

        when(todoService.saveTodo(any(TodoDto.class))).thenReturn(mockResponse);

        // Performing the HTTP request and verifying the response
        MvcResult result = mockMvc.perform(post("/v1/api/todo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value("Test Todo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.description").value("Test Description"))
                .andReturn();

        // Debugging: Print the actual response
        System.out.println("Response: " + result.getResponse().getContentAsString());
    }

}