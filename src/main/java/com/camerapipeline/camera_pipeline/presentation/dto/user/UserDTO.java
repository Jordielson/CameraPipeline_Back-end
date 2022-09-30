package com.camerapipeline.camera_pipeline.presentation.dto.user;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Data
public class UserDTO {
    @Schema(
		name = "token",
		description = "Token to authentication",
		example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvQ"+
		"GdtYWlsLmNvbSIsInNjb3BlcyI6IlJPTEVfVVNFUiIsImlhdC"+
		"I6MTY2MjkyMzU0OSwiZXhwIjoxNjY0MTIzNTQ5fQ.NGoBjVtnURZ"+
		"x_P0t-istEJEHXDetbRZbrVYPqnaIeOU",
		type = "string",
		required = true
	)
    private String token;
    @Schema(
		name = "type",
		description = "Type authentication",
		example = "Bearer",
		type = "string",
		required = true
	)
    private String type;
    @Schema(
		name = "login",
		description = "Registered user email",
		example = "antonio@email.com",
		type = "string",
		required = true
	)
	private String login;
    @Schema(
        title = "Roles of user",
        name = "roles",
        type = "[string]",
        example = "["+
            "ROLE_USER"+
          "]",
		required = true
    )
    private List<String> roles;
}
