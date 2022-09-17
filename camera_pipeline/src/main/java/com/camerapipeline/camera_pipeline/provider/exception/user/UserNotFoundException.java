package com.camerapipeline.camera_pipeline.provider.exception.user;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends EntityNotFoundException {
    private final int id;

    public UserNotFoundException(int id) {
        super("User not found with id: " + id);
        this.id = id;
    }

    public Integer getUserId() {
        return id;
    }

}
