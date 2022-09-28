package com.camerapipeline.camera_pipeline.provider.exception.user;

import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;

import lombok.Getter;

@Getter
public class SamePasswordException extends BusinessException {
    private final Integer id;
    
    public SamePasswordException(String message, Integer id) {
        super(
            message, 
            "ERR_SAME_PASSWORD", 
            "Unable to change password as the requested password is already in use"
        );
        this.id = id;
    }


    
}
