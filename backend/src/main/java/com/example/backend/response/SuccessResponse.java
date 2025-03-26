package com.example.backend.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> implements Response {

    private List<T> dataList;

    private T data;

    private String message;
    public SuccessResponse(List<T> dataList) {
        this.dataList = dataList;
    }
    public SuccessResponse(T data) {
        this.data = data;
    }
    public SuccessResponse(String message) {
        this.message = message;
    }

    public List<T> getDataList() {
        return dataList;
    }
    public T getData() {
        return data;
    }
    public String getMessage() {
        return message;
    }


}
