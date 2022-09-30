package com.camerapipeline.camera_pipeline.presentation.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.core.validation.ExtendedEmailValidator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
public class LoginDTO {
	@Schema(
		name = "login",
		description = "Registered user email",
		example = "antonio@email.com",
		type = "string",
		required = true
	)
    @ExtendedEmailValidator
	public String login;
	
	@Schema(
		name = "password",
		description = "Registered user password",
		example = "Sadm@klds.d25",
		type = "string",
		required = true
	)
	@NotBlank
	@Size(min = 6, message = "Password must be longer than 6 characters")
	public String password;
}
