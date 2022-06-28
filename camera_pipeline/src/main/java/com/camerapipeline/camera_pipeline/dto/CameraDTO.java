package com.camerapipeline.camera_pipeline.dto;

import java.util.Objects;

import lombok.Data;

@Data
public class CameraDTO {
    private int id;
    private String name;
    private String URL;
    private boolean isPrivate;
    private int fpsLimiter;


    public CameraDTO() {
    }

    public CameraDTO(int id, String name, String URL, boolean isPrivate, int fpsLimiter) {
        this.id = id;
        this.name = name;
        this.URL = URL;
        this.isPrivate = isPrivate;
        this.fpsLimiter = fpsLimiter;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getFpsLimiter() {
        return this.fpsLimiter;
    }

    public void setFpsLimiter(int fpsLimiter) {
        this.fpsLimiter = fpsLimiter;
    }

    public CameraDTO id(int id) {
        setId(id);
        return this;
    }

    public CameraDTO name(String name) {
        setName(name);
        return this;
    }

    public CameraDTO URL(String URL) {
        setURL(URL);
        return this;
    }

    public CameraDTO isPrivate(boolean isPrivate) {
        setIsPrivate(isPrivate);
        return this;
    }

    public CameraDTO fpsLimiter(int fpsLimiter) {
        setFpsLimiter(fpsLimiter);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CameraDTO)) {
            return false;
        }
        CameraDTO cameraDTO = (CameraDTO) o;
        return id == cameraDTO.id && Objects.equals(name, cameraDTO.name) && Objects.equals(URL, cameraDTO.URL) && isPrivate == cameraDTO.isPrivate && fpsLimiter == cameraDTO.fpsLimiter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, URL, isPrivate, fpsLimiter);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", URL='" + getURL() + "'" +
            ", isPrivate='" + isIsPrivate() + "'" +
            ", fpsLimiter='" + getFpsLimiter() + "'" +
            "}";
    }
}
