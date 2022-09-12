package com.camerapipeline.camera_pipeline.model.entities.camera;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.user.User;

@Entity
public class Camera implements ModelAbstract<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @ManyToOne
    private User user;
    @NotBlank
    private String name;
    @NotBlank
    private String URL;
    @NotNull
    private Boolean isPrivate;
    @NotNull
    private Boolean isActive;
    
	@Valid
	@Embedded
	private Coordinate coordinate;

    private Integer fpsLimiter;


    public Camera() {
    }

    public Camera(Integer id, User user, String name, String URL, Boolean isPrivate, Boolean isActive, Coordinate coordinate, Integer fpsLimiter) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.URL = URL;
        this.isPrivate = isPrivate;
        this.isActive = isActive;
        this.coordinate = coordinate;
        this.fpsLimiter = fpsLimiter;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Boolean isIsPrivate() {
        return this.isPrivate;
    }

    public Boolean getIsPrivate() {
        return this.isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Boolean isIsActive() {
        return this.isActive;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getFpsLimiter() {
        return this.fpsLimiter;
    }

    public void setFpsLimiter(Integer fpsLimiter) {
        this.fpsLimiter = fpsLimiter;
    }

    public Camera id(Integer id) {
        setId(id);
        return this;
    }

    public Camera user(User user) {
        setUser(user);
        return this;
    }

    public Camera name(String name) {
        setName(name);
        return this;
    }

    public Camera URL(String URL) {
        setURL(URL);
        return this;
    }

    public Camera isPrivate(Boolean isPrivate) {
        setIsPrivate(isPrivate);
        return this;
    }

    public Camera isActive(Boolean isActive) {
        setIsActive(isActive);
        return this;
    }

    public Camera coordinate(Coordinate coordinate) {
        setCoordinate(coordinate);
        return this;
    }

    public Camera fpsLimiter(Integer fpsLimiter) {
        setFpsLimiter(fpsLimiter);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Camera)) {
            return false;
        }
        Camera camera = (Camera) o;
        return Objects.equals(id, camera.id) && Objects.equals(user, camera.user) && Objects.equals(name, camera.name) && Objects.equals(URL, camera.URL) && isPrivate == camera.isPrivate && isActive == camera.isActive && Objects.equals(coordinate, camera.coordinate) && Objects.equals(fpsLimiter, camera.fpsLimiter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, URL, isPrivate, isActive, coordinate, fpsLimiter);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", name='" + getName() + "'" +
            ", URL='" + getURL() + "'" +
            ", isPrivate='" + isIsPrivate() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", coordinate='" + getCoordinate() + "'" +
            ", fpsLimiter='" + getFpsLimiter() + "'" +
            "}";
    }    
}
