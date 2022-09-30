package com.camerapipeline.camera_pipeline.presentation.dto.auth;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidTokenResponse {
    @Schema(
		name = "token",
		description = "Token that has been verified if it is valid",
		example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvQ"+
		"GdtYWlsLmNvbSIsInNjb3BlcyI6IlJPTEVfVVNFUiIsImlhdC"+
		"I6MTY2MjkyMzU0OSwiZXhwIjoxNjY0MTIzNTQ5fQ.NGoBjVtnURZ"+
		"x_P0t-istEJEHXDetbRZbrVYPqnaIeOU",
		type = "string"
	)
    private String token;
    @Schema(
		name = "expirationTime",
		description = "Token expiration date",
		type = "date-time"
	)
	private LocalDateTime expirationTime;
    @Schema(
		name = "isValid",
		description = "Reponse if the token is valid",
		example = "true",
		type = "boolean"
	)
	private boolean isValid;
}
