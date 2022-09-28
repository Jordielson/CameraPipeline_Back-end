package com.camerapipeline.camera_pipeline.core.handlers.advice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionCode;
import com.camerapipeline.camera_pipeline.core.handlers.exception.ExceptionMessage;
import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;

import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInavalidArgument(MethodArgumentNotValidException ex) {
        List <String> messages = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            messages.add(String.format("%s - %s", error.getField(), error.getDefaultMessage()));
        });
        final ExceptionMessage exceptionMessage 
            = new ExceptionMessage(
                HttpStatus.BAD_REQUEST, 
                "ERR_INAVALID_ARGUMENT", 
                ex.getLocalizedMessage(), 
                messages
            );

        log.warn("ERR_INAVALID_ARGUMENT - [{}].", messages, ex);
        
        return  new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex) {
        final List<String> messages = new ArrayList<>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            messages.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        }
        final ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.BAD_REQUEST, "ERR_CONSTRAINT_VIOLATION", ex.getLocalizedMessage(), messages);
        log.warn("ERR_CONSTRAINT_VIOLATION - [{}].", messages, ex);

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityConstraintViolation(final DataIntegrityViolationException ex) {
        final ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.BAD_REQUEST, "ERR_CONSTRAINT_VIOLATION", ex.getLocalizedMessage(), "Constraint Violation");
        log.warn("ERR_CONSTRAINT_VIOLATION - [{}].", ex.getMessage(), ex);

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(EntityNotFoundException ex) {
        final ExceptionMessage exceptionMessage 
            = new ExceptionMessage(
                HttpStatus.NOT_FOUND, 
                "ERR_NOT_FOUND", 
                ex.getLocalizedMessage(), 
                ex.getMessage()
                );

        log.warn("ERR_NOT_FOUND - [{}].", ex.getMessage(), ex);

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleBadCredentials(final AccessDeniedException ex) {
        final ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.FORBIDDEN, "ERR_ACCESS_DENIED", ex.getLocalizedMessage(), "Access denied");
        log.warn("ERR_ACCESS_DENIED - [{}].", ex.getMessage(), ex);

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {
        final String message = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        final ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.BAD_REQUEST, "ERR_ARGUMENT_TYPE_MISMATCH", ex.getLocalizedMessage(), message);
        log.warn("ERR_ARGUMENT_TYPE_MISMATCH - [{}].", message, ex);

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentials(final BadCredentialsException ex) {
        final ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.FORBIDDEN, "ERR_BAD_CREDENTIALS", ex.getLocalizedMessage(), "Bad credentials");
        log.warn("ERR_BAD_CREDENTIALS_VIOLATION - [{}].", ex.getMessage(), ex);

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<Object> handleCredentialsExpired(final CredentialsExpiredException ex) {
        final ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.BAD_REQUEST, "ERR_BAD_REQUEST", ex.getLocalizedMessage(), "BAD_REQUEST");
        log.warn("ERR_BAD_REQUEST_VIOLATION - [{}].", ex.getMessage(), ex);

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleExpiredJwt(final SignatureException ex) {
        final ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.BAD_REQUEST, "ERR_BAD_REQUEST", ex.getLocalizedMessage(), "BAD_REQUEST");
        log.warn("ERR_BAD_REQUEST_VIOLATION - [{}].", ex.getMessage(), ex);

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    @ExceptionHandler({AuthorizationServiceException.class})
    public ResponseEntity<Object> handleAuthoritzation(final AuthorizationServiceException ex) {
        final ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.UNAUTHORIZED, "ERR_UNAUTHORIZED", ex.getLocalizedMessage(), "Unauthorized");
        log.warn("ERR_UNAUTHORIZED - [{}].", ex.getMessage(), ex);

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }
    
    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusiness(final BusinessException ex) {
        final ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getCode(), ex.getMessage(), ex.getSpecificMessage());
        log.warn("[{}] - [{}].", ex.getCode(), ex.getMessage(), ex, ex.getCause());

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<Object> handleAllExceptions(final Exception ex) {
        String key= getKeyForException(ex);
        final ExceptionMessage exceptionMessage = new ExceptionMessage(getStatusForException(ex), key, ex.getLocalizedMessage(), getHumanReadeableTextForException(ex));
        log.warn("[{}] - [{}].", key, ex.getMessage(), ex);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), exceptionMessage.getStatus());
    }

    private HttpStatus getStatusForException(final Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getClass().isAnnotationPresent(ResponseStatus.class)) {
            status = ex.getClass().getAnnotation(ResponseStatus.class).value();
        }
        return status;
    }

    private String getKeyForException(final Exception ex) {
        String key = "ERR_GENERAL";
        if (ex.getClass().isAnnotationPresent(ExceptionCode.class)) {
            key = ex.getClass().getAnnotation(ExceptionCode.class).value();
        }
        return key;
    }

    private String getHumanReadeableTextForException(final Exception ex) {
        String text = "ERR_GENERAL";
        if (ex.getClass().isAnnotationPresent(ExceptionCode.class)) {
            text = ex.getClass().getAnnotation(ExceptionCode.class).text();
        }
        return text;
    }
}
