package com.camerapipeline.camera_pipeline.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Camera {
    @Id
    private String ip;
    @NotBlank
    private String name;
    @NotNull
    private int heigth;
    @NotNull
    private int width;
    @NotNull
    private boolean isColored;
    private String model;
    private int viewAngle;
    @NotNull
    private boolean nightVision;
    private String manufacturer;
    @NotNull
    private int fps;
    @NotNull
    @ManyToOne
    private User user;


    public Camera() {
    }

    public Camera(String ip, String name, int heigth, int width, boolean isColored, String model, int viewAngle, boolean nightVision, String manufacturer, int fps, User user) {
        this.ip = ip;
        this.name = name;
        this.heigth = heigth;
        this.width = width;
        this.isColored = isColored;
        this.model = model;
        this.viewAngle = viewAngle;
        this.nightVision = nightVision;
        this.manufacturer = manufacturer;
        this.fps = fps;
        this.user = user;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeigth() {
        return this.heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isIsColored() {
        return this.isColored;
    }

    public boolean getIsColored() {
        return this.isColored;
    }

    public void setIsColored(boolean isColored) {
        this.isColored = isColored;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getViewAngle() {
        return this.viewAngle;
    }

    public void setViewAngle(int viewAngle) {
        this.viewAngle = viewAngle;
    }

    public boolean isNightVision() {
        return this.nightVision;
    }

    public boolean getNightVision() {
        return this.nightVision;
    }

    public void setNightVision(boolean nightVision) {
        this.nightVision = nightVision;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getFps() {
        return this.fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public Camera ip(String ip) {
        setIp(ip);
        return this;
    }

    public Camera name(String name) {
        setName(name);
        return this;
    }

    public Camera heigth(int heigth) {
        setHeigth(heigth);
        return this;
    }

    public Camera width(int width) {
        setWidth(width);
        return this;
    }

    public Camera isColored(boolean isColored) {
        setIsColored(isColored);
        return this;
    }

    public Camera model(String model) {
        setModel(model);
        return this;
    }

    public Camera viewAngle(int viewAngle) {
        setViewAngle(viewAngle);
        return this;
    }

    public Camera nightVision(boolean nightVision) {
        setNightVision(nightVision);
        return this;
    }

    public Camera manufacturer(String manufacturer) {
        setManufacturer(manufacturer);
        return this;
    }

    public Camera fps(int fps) {
        setFps(fps);
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
        return ip == camera.ip && Objects.equals(name, camera.name) && heigth == camera.heigth && width == camera.width && isColored == camera.isColored && Objects.equals(model, camera.model) && viewAngle == camera.viewAngle && nightVision == camera.nightVision && Objects.equals(manufacturer, camera.manufacturer) && fps == camera.fps;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, name, heigth, width, isColored, model, viewAngle, nightVision, manufacturer, fps);
    }

    @Override
    public String toString() {
        return "{" +
            " ip='" + getIp() + "'" +
            ", name='" + getName() + "'" +
            ", heigth='" + getHeigth() + "'" +
            ", width='" + getWidth() + "'" +
            ", isColored='" + isIsColored() + "'" +
            ", model='" + getModel() + "'" +
            ", viewAngle='" + getViewAngle() + "'" +
            ", nightVision='" + isNightVision() + "'" +
            ", manufacturer='" + getManufacturer() + "'" +
            ", fps='" + getFps() + "'" +
            "}";
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Camera user(User user) {
        setUser(user);
        return this;
    }

}
