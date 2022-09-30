package com.camerapipeline.camera_pipeline.presentation.dto.user;

import javax.validation.constraints.NotBlank;

import com.camerapipeline.camera_pipeline.core.validation.ExtendedEmailValidator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class ForgotPasswordDTO {
    @Schema(
		name = "email",
		description = "Registered user email",
		example = "antonio@email.com",
		type = "string",
		required = true
	)
    @ExtendedEmailValidator
	public String email;
    @Schema(
		name = "redirect",
		description = "Redirect link to reset password",
		example = "https://camerapipeline.netilify/password-reset",
		type = "string",
		required = true
	)
    @NotBlank
    public String redirect;
}
