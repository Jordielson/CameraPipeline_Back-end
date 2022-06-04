package com.camerapipeline.camera_pipeline.dto;

import javax.validation.constraints.NotBlank;

public class LoginDto {
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
