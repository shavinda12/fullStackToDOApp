package com.example.backend.controller;


import com.example.backend.dto.TodoDto;
import com.example.backend.response.Response;
import com.example.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "v1/api/todo")
@CrossOrigin
public class TodoController {
    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    public TodoController() {

    }

    @GetMapping("/")
    public ResponseEntity<Response> getFirstFiveTodos(){
        System.out.println("inside the controller");
        return todoService.getFirstFiveTodos();
    }

    @PostMapping("/")
    public ResponseEntity<Response> postTodo(@RequestBody TodoDto todoDto){
        System.out.println(todoDto);
        return todoService.saveTodo(todoDto);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Response> removeTodo(@PathVariable  long taskId){
        return todoService.deleteTodo(taskId);
    }

}
