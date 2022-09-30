package com.camerapipeline.camera_pipeline.presentation.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
public class ValidTokenDTO {
	@Schema(
		name = "token",
		description = "Token to check if it is valid",
		example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvQ"+
		"GdtYWlsLmNvbSIsInNjb3BlcyI6IlJPTEVfVVNFUiIsImlhdC"+
		"I6MTY2MjkyMzU0OSwiZXhwIjoxNjY0MTIzNTQ5fQ.NGoBjVtnURZ"+
		"x_P0t-istEJEHXDetbRZbrVYPqnaIeOU",
		type = "string",
		required = true
	)
	private String token;
}
