package com.camerapipeline.camera_pipeline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    private final int id;

    public UserNotFoundException(int id) {
        super("user-not-found-" + id);
        this.id = id;
    }

    public Integer getUserId() {
        return id;
    }

}
