package com.camerapipeline.camera_pipeline.presentation.dto.camera;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.model.entities.camera.Coordinate;

import lombok.Data;

@Data
public class CameraDTO {
    private int id;
    @NotBlank
	@Size(max=50)
    private String name;
    @NotBlank
    private String URL;
    @NotNull
    private Boolean isPrivate;
    private Boolean isActive;
    private Coordinate coordinate;
    private Integer fpsLimiter;

    public CameraDTO() {
    }

    public CameraDTO(int id, String name, String URL, Boolean isPrivate, Boolean isActive, Coordinate coordinate, Integer fpsLimiter) {
        this.id = id;
        this.name = name;
        this.URL = URL;
        this.isPrivate = isPrivate;
        this.isActive = isActive;
        this.coordinate = coordinate;
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

    public CameraDTO isPrivate(Boolean isPrivate) {
        setIsPrivate(isPrivate);
        return this;
    }

    public CameraDTO isActive(Boolean isActive) {
        setIsActive(isActive);
        return this;
    }

    public CameraDTO coordinate(Coordinate coordinate) {
        setCoordinate(coordinate);
        return this;
    }

    public CameraDTO fpsLimiter(Integer fpsLimiter) {
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
        return id == cameraDTO.id && Objects.equals(name, cameraDTO.name) && Objects.equals(URL, cameraDTO.URL) && isPrivate == cameraDTO.isPrivate && isActive == cameraDTO.isActive && Objects.equals(coordinate, cameraDTO.coordinate) && Objects.equals(fpsLimiter, cameraDTO.fpsLimiter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, URL, isPrivate, isActive, coordinate, fpsLimiter);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", URL='" + getURL() + "'" +
            ", isPrivate='" + isIsPrivate() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", coordinate='" + getCoordinate() + "'" +
            ", fpsLimiter='" + getFpsLimiter() + "'" +
            "}";
    }
}