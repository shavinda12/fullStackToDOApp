package com.example.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ErrorResponse implements Response{
    private final String errorMessage;
    public ErrorResponse(String error) {
        this.errorMessage = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
