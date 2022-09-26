package com.camerapipeline.camera_pipeline.presentation.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.core.validation.ExtendedEmailValidator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
public class LoginDTO {
    @ExtendedEmailValidator
	public String login;
	
	@NotBlank
	@Size(min = 6, message = "Password must be longer than 6 characters")
	public String password;
}
