package com.camerapipeline.camera_pipeline.dto.user;

import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class UserDTO {
    private String token;
    private String type;
	private String login;
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String token, String type, String login, List<String> roles) {
        this.token = token;
        this.type = type;
        this.login = login;
        this.roles = roles;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public UserDTO token(String token) {
        setToken(token);
        return this;
    }

    public UserDTO type(String type) {
        setType(type);
        return this;
    }

    public UserDTO login(String login) {
        setLogin(login);
        return this;
    }

    public UserDTO roles(List<String> roles) {
        setRoles(roles);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDTO)) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(token, userDTO.token) && Objects.equals(type, userDTO.type) && Objects.equals(login, userDTO.login) && Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, type, login, roles);
    }

    @Override
    public String toString() {
        return "{" +
            " token='" + getToken() + "'" +
            ", type='" + getType() + "'" +
            ", login='" + getLogin() + "'" +
            ", roles='" + getRoles() + "'" +
            "}";
    }
}
