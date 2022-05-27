package com.camerapipeline.camera_pipeline.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Pipeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    private String description;
    @CreatedDate
    @Column(name = "creation_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;
    @LastModifiedDate
    @Column(name = "last_change", columnDefinition = "TIMESTAMP")
    private LocalDateTime modificationTime;
    @NotNull
    private boolean isActive;
    @NotNull
    @ManyToOne
    private GroupPipeline groupPipeline;


    public Pipeline() {
    }

    public Pipeline(int id, String name, String description, LocalDateTime creationDate, LocalDateTime modificationTime, boolean isActive, GroupPipeline groupPipeline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.modificationTime = modificationTime;
        this.isActive = isActive;
        this.groupPipeline = groupPipeline;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModificationTime() {
        return this.modificationTime;
    }

    public void setModificationTime(LocalDateTime modificationTime) {
        this.modificationTime = modificationTime;
    }

    public boolean isIsActive() {
        return this.isActive;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public GroupPipeline getGroupPipeline() {
        return this.groupPipeline;
    }

    public void setGroupPipeline(GroupPipeline groupPipeline) {
        this.groupPipeline = groupPipeline;
    }

    public Pipeline id(int id) {
        setId(id);
        return this;
    }

    public Pipeline name(String name) {
        setName(name);
        return this;
    }

    public Pipeline description(String description) {
        setDescription(description);
        return this;
    }

    public Pipeline creationDate(LocalDateTime creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    public Pipeline modificationTime(LocalDateTime modificationTime) {
        setModificationTime(modificationTime);
        return this;
    }

    public Pipeline isActive(boolean isActive) {
        setIsActive(isActive);
        return this;
    }

    public Pipeline groupPipeline(GroupPipeline groupPipeline) {
        setGroupPipeline(groupPipeline);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pipeline)) {
            return false;
        }
        Pipeline pipeline = (Pipeline) o;
        return id == pipeline.id && Objects.equals(name, pipeline.name) && Objects.equals(description, pipeline.description) && Objects.equals(creationDate, pipeline.creationDate) && Objects.equals(modificationTime, pipeline.modificationTime) && isActive == pipeline.isActive && Objects.equals(groupPipeline, pipeline.groupPipeline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, creationDate, modificationTime, isActive, groupPipeline);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", modificationTime='" + getModificationTime() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", groupPipeline='" + getGroupPipeline() + "'" +
            "}";
    }

}
