package com.camerapipeline.camera_pipeline.model.camera;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.user.User;

@Entity
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne
    private User user;
    @NotBlank
    private String name;
    @NotBlank
    private String URL;
    @NotNull
    private boolean isPrivate;

    @Transient
    private Double[] coordinates;

    private int fpsLimiter;


    public Camera() {
    }

    public Camera(int id, User user, String name, String URL, boolean isPrivate, Double[] coordinates, int fpsLimiter) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.URL = URL;
        this.isPrivate = isPrivate;
        this.coordinates = coordinates;
        this.fpsLimiter = fpsLimiter;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public boolean isIsPrivate() {
        return this.isPrivate;
    }

    public boolean getIsPrivate() {
        return this.isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Double[] getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getFpsLimiter() {
        return this.fpsLimiter;
    }

    public void setFpsLimiter(int fpsLimiter) {
        this.fpsLimiter = fpsLimiter;
    }

    public Camera id(int id) {
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

    public Camera isPrivate(boolean isPrivate) {
        setIsPrivate(isPrivate);
        return this;
    }

    public Camera coordinates(Double[] coordinates) {
        setCoordinates(coordinates);
        return this;
    }

    public Camera fpsLimiter(int fpsLimiter) {
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
        return id == camera.id && Objects.equals(user, camera.user) && Objects.equals(name, camera.name) && Objects.equals(URL, camera.URL) && isPrivate == camera.isPrivate && Objects.equals(coordinates, camera.coordinates) && fpsLimiter == camera.fpsLimiter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, URL, isPrivate, coordinates, fpsLimiter);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", name='" + getName() + "'" +
            ", URL='" + getURL() + "'" +
            ", isPrivate='" + isIsPrivate() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            ", fpsLimiter='" + getFpsLimiter() + "'" +
            "}";
    }
}
