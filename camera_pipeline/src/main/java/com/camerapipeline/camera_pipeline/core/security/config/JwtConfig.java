package com.camerapipeline.camera_pipeline.core.security.config;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtConfig {
    private String clientSecret;
    private int tokenValidityInSeconds;


    public JwtConfig() {
    }

    public JwtConfig(String clientSecret, int tokenValidityInSeconds) {
        this.clientSecret = clientSecret;
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getTokenValidityInSeconds() {
        return this.tokenValidityInSeconds;
    }

    public void setTokenValidityInSeconds(int tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public JwtConfig clientSecret(String clientSecret) {
        setClientSecret(clientSecret);
        return this;
    }

    public JwtConfig tokenValidityInSeconds(int tokenValidityInSeconds) {
        setTokenValidityInSeconds(tokenValidityInSeconds);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JwtConfig)) {
            return false;
        }
        JwtConfig jwtConfig = (JwtConfig) o;
        return Objects.equals(clientSecret, jwtConfig.clientSecret) && tokenValidityInSeconds == jwtConfig.tokenValidityInSeconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientSecret, tokenValidityInSeconds);
    }

    @Override
    public String toString() {
        return "{" +
            " clientSecret='" + getClientSecret() + "'" +
            ", tokenValidityInSeconds='" + getTokenValidityInSeconds() + "'" +
            "}";
    }

}
