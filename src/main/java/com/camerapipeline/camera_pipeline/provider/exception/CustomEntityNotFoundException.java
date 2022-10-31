package com.camerapipeline.camera_pipeline.provider.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomEntityNotFoundException extends EntityNotFoundException {
    private final String id;
    private final Throwable cause;

    public CustomEntityNotFoundException(String entityName, String id) {
        super(String.format("%s with id %s was not found", entityName, id));
        this.cause = new EntityNotFoundException();
        this.id = id;
    }

    public CustomEntityNotFoundException(String message, String id, Throwable cause) {
        super(message);
        this.cause = cause;
        this.id = message;
    }

    public String getId() {
        return id;
    }

    public Throwable getCause() {
        return cause;
    }
}
