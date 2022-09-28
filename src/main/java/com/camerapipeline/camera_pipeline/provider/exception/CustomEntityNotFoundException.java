package com.camerapipeline.camera_pipeline.provider.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomEntityNotFoundException extends EntityNotFoundException {
    private final String id;

    public CustomEntityNotFoundException(String entityName, String id) {
        super(String.format("%s with id %s was not found", entityName, id));
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
