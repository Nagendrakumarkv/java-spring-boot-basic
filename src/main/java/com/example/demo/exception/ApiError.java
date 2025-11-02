package com.example.demo.exception;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private List<String> messages;

    public ApiError() {}

    public ApiError(int status, String error, String path, List<String> messages) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.path = path;
        this.messages = messages;
    }

}
