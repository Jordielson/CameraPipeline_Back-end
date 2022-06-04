package com.camerapipeline.camera_pipeline.dto;

import java.util.List;
import java.util.Objects;

public class UserDto {
    private String token;
    private String type = "Bearer";
	private Integer id;
	private String login;
    private List<String> roles;

    public UserDto() {
    }

    public UserDto(String token, Integer id, String login, List<String> roles) {
        this.token = token;
        this.id = id;
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UserDto token(String token) {
        setToken(token);
        return this;
    }

    public UserDto type(String type) {
        setType(type);
        return this;
    }

    public UserDto id(Integer id) {
        setId(id);
        return this;
    }

    public UserDto login(String login) {
        setLogin(login);
        return this;
    }

    public UserDto roles(List<String> roles) {
        setRoles(roles);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(token, userDto.token) && Objects.equals(type, userDto.type) && Objects.equals(id, userDto.id) && Objects.equals(login, userDto.login) && Objects.equals(roles, userDto.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, type, id, login, roles);
    }

    @Override
    public String toString() {
        return "{" +
            " token='" + getToken() + "'" +
            ", type='" + getType() + "'" +
            ", id='" + getId() + "'" +
            ", login='" + getLogin() + "'" +
            ", roles='" + getRoles() + "'" +
            "}";
    }
}
