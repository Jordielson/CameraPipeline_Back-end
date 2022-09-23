package com.camerapipeline.camera_pipeline.presentation.dto.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Data
public class UserDTO {
    private String token;
    private String type;
	private String login;
    private List<String> roles;
}
