package com.camerapipeline.camera_pipeline.presentation.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.core.validation.ExtendedEmailValidator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class UserResquest {
    @NotNull
    @ExtendedEmailValidator
    private String email;
    @NotNull
    @Size(min = 6, message = "Password must be longer than 6 characters")
    private String password;
}
