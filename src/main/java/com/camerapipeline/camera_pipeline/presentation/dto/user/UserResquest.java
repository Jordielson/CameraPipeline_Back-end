package com.camerapipeline.camera_pipeline.presentation.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.core.validation.ExtendedEmailValidator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class UserResquest {
    @Schema(
        title = "Email",
        name = "email",
        type = "string",
        example = "antonio@email.com",
		required = true
    )
    @NotNull
    @ExtendedEmailValidator
    private String email;
    @Schema(
        title = "Password",
		name = "password",
		example = "Sadm@klds.d25",
		type = "string",
		required = true
	)
    @NotNull
    @Size(min = 6, message = "Password must be longer than 6 characters")
    private String password;
}
