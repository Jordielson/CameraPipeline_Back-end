package com.camerapipeline.camera_pipeline.presentation.dto.user;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    @Schema(
        title = "User identifier",
        name = "id",
        type = "int",
        example = "2532",
		required = true
    )
    private Integer id;
    @Schema(
        title = "Email",
        name = "email",
        type = "string",
        example = "antonio@email.com",
		required = true
    )
    private String email;
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
