package com.camerapipeline.camera_pipeline.presentation.dto.user;

import javax.validation.constraints.NotBlank;

import com.camerapipeline.camera_pipeline.core.validation.ExtendedEmailValidator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class ForgotPasswordDTO {
    @ExtendedEmailValidator
	public String email;
    @NotBlank
    public String redirect;
}
