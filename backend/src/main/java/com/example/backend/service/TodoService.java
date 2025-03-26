package com.example.backend.service;

import com.example.backend.dto.TodoDto;
import com.example.backend.model.TodoModel;
import com.example.backend.repository.TodoRepo;
import com.example.backend.response.ErrorResponse;
import com.example.backend.response.Response;
import com.example.backend.response.SuccessResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoService {
    private TodoRepo todoRepo;

    private ModelMapper modelMapper;


    @Autowired
    public TodoService(TodoRepo todoRepo, ModelMapper modelMapper) {
        this.todoRepo = todoRepo;
        this.modelMapper = modelMapper;
    }

    public TodoService(){

    }




    public ResponseEntity<Response> getFirstFiveTodos(){
        try{
            List<TodoModel>todoList=todoRepo.getTodoList();
            List<TodoDto> toDoDtoList=modelMapper.map(todoList,new TypeToken<List<TodoDto>>() {}.getType());
            if(todoList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<TodoDto>(toDoDtoList));
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<TodoDto>(toDoDtoList));
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("internal server error"));
        }
    }

    public ResponseEntity<Response> saveTodo(TodoDto todoDto){
        try{
            TodoModel savedTodoModel=todoRepo.save(modelMapper.map(todoDto,TodoModel.class));
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<TodoDto>(modelMapper.map(savedTodoModel,TodoDto.class)));
        }catch(Exception e){
            System.out.println("exception has occured "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Something went wrong"));
        }
    }

    public ResponseEntity<Response> deleteTodo(long taskId){
        try{
            int isUpdated=todoRepo.updateIsCompletedToTrue(taskId);
            if(isUpdated>0){
                return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<TodoDto>("Task "+taskId+ " successfully updated !"));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Task "+taskId+ " not updated !"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Something went wrong"));
        }

    }
}
