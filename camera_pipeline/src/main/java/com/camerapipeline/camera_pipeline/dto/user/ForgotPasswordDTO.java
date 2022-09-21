package com.camerapipeline.camera_pipeline.dto.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class ForgotPasswordDTO {
    @NotBlank
	public String email;
}
