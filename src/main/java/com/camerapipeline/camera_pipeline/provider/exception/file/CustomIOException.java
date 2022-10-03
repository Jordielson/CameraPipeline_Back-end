package com.camerapipeline.camera_pipeline.provider.exception.file;

import org.springframework.http.HttpStatus;

public class CustomIOException extends RuntimeException {
    private final String id;
    private final String code;
    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;


    public CustomIOException(String message, String code, String id, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
