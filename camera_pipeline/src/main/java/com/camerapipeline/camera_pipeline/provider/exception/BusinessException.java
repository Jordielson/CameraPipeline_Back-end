package com.camerapipeline.camera_pipeline.provider.exception;

public class BusinessException extends RuntimeException {

	public BusinessException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
	
	public BusinessException(String mensagem) {
		super(mensagem);
	}
}
