package com.camerapipeline.camera_pipeline.presentation.dto.auth;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
	public String login;
	
	@NotBlank
	public String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
