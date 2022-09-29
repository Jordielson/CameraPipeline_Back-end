package com.camerapipeline.camera_pipeline.provider.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	private final String code;
	private final String specificMessage;
	private final HttpStatus status;

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.code = "ERR_INTERNAL_SERVER_ERROR";
		this.specificMessage = message;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public BusinessException(String message) {
		super(message);
		this.code = "ERR_INTERNAL_SERVER_ERROR";
		this.specificMessage = message;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public BusinessException(String message, String code, String specificMessage) {
		super(message);
		this.code = code;
		this.specificMessage = specificMessage;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public BusinessException(String message, String code, String specificMessage, HttpStatus status) {
		super(message);
		this.code = code;
		this.specificMessage = specificMessage;
		this.status = status;
	}
}
