package com.camerapipeline.camera_pipeline.presentation.dto.auth;

import java.util.Objects;

public class ValidTokenDTO {
	private String token;

    public ValidTokenDTO() {
    }

    public ValidTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ValidTokenDTO token(String token) {
        setToken(token);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ValidTokenDTO)) {
            return false;
        }
        ValidTokenDTO validTokenDTO = (ValidTokenDTO) o;
        return Objects.equals(token, validTokenDTO.token);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(token);
    }

    @Override
    public String toString() {
        return "{" +
            " token='" + getToken() + "'" +
            "}";
    }
}
