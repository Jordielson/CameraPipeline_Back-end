package com.camerapipeline.camera_pipeline.core.handlers.exception;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Data
public class ExceptionMessage {
    private String code;
    private HttpStatus status;
    private String originalExceptionMessage;
    private List<String> messages;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private ZonedDateTime timestamp;

    public ExceptionMessage(HttpStatus status, String code, String originalExceptionMessage, List<String> messages) {
        this.status = status;
        this.code = code;
        this.originalExceptionMessage = originalExceptionMessage;
        this.messages = messages;
        this.timestamp = ZonedDateTime.now();
    }

    public ExceptionMessage(HttpStatus status, String code, String originalExceptionMessage, String message) {
        this.status = status;
        this.code = code;
        this.originalExceptionMessage = originalExceptionMessage;
        messages = Arrays.asList(message);
        this.timestamp = ZonedDateTime.now();
    }
}
