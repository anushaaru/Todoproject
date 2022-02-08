package com.todoapp.todoproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskHandleException extends RuntimeException{

    public TaskHandleException() {
        super();
    }

    public TaskHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskHandleException(String message) {
        super(message);
    }

    public TaskHandleException(Throwable cause) {
        super(cause);
    }

}
