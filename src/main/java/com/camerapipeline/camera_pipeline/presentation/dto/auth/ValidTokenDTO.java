package com.camerapipeline.camera_pipeline.presentation.dto.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
public class ValidTokenDTO {
	private String token;
}
