package com.camerapipeline.camera_pipeline.presentation.dto.auth;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ValidTokenResponse {
    private String token;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime expirationTime;
	private boolean isValid;

    public ValidTokenResponse() {
    }

    public ValidTokenResponse(String token, LocalDateTime expirationTime, boolean isValid) {
        this.token = token;
        this.expirationTime = expirationTime;
        this.isValid = isValid;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTime() {
        return this.expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isIsValid() {
        return this.isValid;
    }

    public boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public ValidTokenResponse token(String token) {
        setToken(token);
        return this;
    }

    public ValidTokenResponse expirationTime(LocalDateTime expirationTime) {
        setExpirationTime(expirationTime);
        return this;
    }

    public ValidTokenResponse isValid(boolean isValid) {
        setIsValid(isValid);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ValidTokenResponse)) {
            return false;
        }
        ValidTokenResponse validTokenResponse = (ValidTokenResponse) o;
        return Objects.equals(token, validTokenResponse.token) && Objects.equals(expirationTime, validTokenResponse.expirationTime) && isValid == validTokenResponse.isValid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expirationTime, isValid);
    }

    @Override
    public String toString() {
        return "{" +
            " token='" + getToken() + "'" +
            ", expirationTime='" + getExpirationTime() + "'" +
            ", isValid='" + isIsValid() + "'" +
            "}";
    }
}
