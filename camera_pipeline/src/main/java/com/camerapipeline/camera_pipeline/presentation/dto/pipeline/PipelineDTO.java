package com.camerapipeline.camera_pipeline.presentation.dto.pipeline;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.camerapipeline.camera_pipeline.presentation.dto.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.PdiDTO;

import lombok.Data;

@Data
public class PipelineDTO {
    private int id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime modificationTime;
    private boolean isActive;
    private int groupPipelineId;
    private List<PdiDTO> PDIList;
    private List<CameraDTO> cameraList;

    public PipelineDTO() {
    }

    public PipelineDTO(int id, String name, String description, LocalDateTime creationDate, LocalDateTime modificationTime, boolean isActive, int groupPipelineId, List<PdiDTO> PDIList, List<CameraDTO> cameraList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.modificationTime = modificationTime;
        this.isActive = isActive;
        this.groupPipelineId = groupPipelineId;
        this.PDIList = PDIList;
        this.cameraList = cameraList;
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

    public int getGroupPipelineId() {
        return this.groupPipelineId;
    }

    public void setGroupPipelineId(int groupPipelineId) {
        this.groupPipelineId = groupPipelineId;
    }

    public List<PdiDTO> getPDIList() {
        return this.PDIList;
    }

    public void setPDIList(List<PdiDTO> PDIList) {
        this.PDIList = PDIList;
    }

    public List<CameraDTO> getCameraList() {
        return this.cameraList;
    }

    public void setCameraList(List<CameraDTO> cameraList) {
        this.cameraList = cameraList;
    }

    public PipelineDTO id(int id) {
        setId(id);
        return this;
    }

    public PipelineDTO name(String name) {
        setName(name);
        return this;
    }

    public PipelineDTO description(String description) {
        setDescription(description);
        return this;
    }

    public PipelineDTO creationDate(LocalDateTime creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    public PipelineDTO modificationTime(LocalDateTime modificationTime) {
        setModificationTime(modificationTime);
        return this;
    }

    public PipelineDTO isActive(boolean isActive) {
        setIsActive(isActive);
        return this;
    }

    public PipelineDTO groupPipelineId(int groupPipelineId) {
        setGroupPipelineId(groupPipelineId);
        return this;
    }

    public PipelineDTO PDIList(List<PdiDTO> PDIList) {
        setPDIList(PDIList);
        return this;
    }

    public PipelineDTO cameraList(List<CameraDTO> cameraList) {
        setCameraList(cameraList);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PipelineDTO)) {
            return false;
        }
        PipelineDTO pipelineDTO = (PipelineDTO) o;
        return id == pipelineDTO.id && Objects.equals(name, pipelineDTO.name) && Objects.equals(description, pipelineDTO.description) && Objects.equals(creationDate, pipelineDTO.creationDate) && Objects.equals(modificationTime, pipelineDTO.modificationTime) && isActive == pipelineDTO.isActive && groupPipelineId == pipelineDTO.groupPipelineId && Objects.equals(PDIList, pipelineDTO.PDIList) && Objects.equals(cameraList, pipelineDTO.cameraList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, creationDate, modificationTime, isActive, groupPipelineId, PDIList, cameraList);
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
            ", groupPipelineId='" + getGroupPipelineId() + "'" +
            ", PDIList='" + getPDIList() + "'" +
            ", cameraList='" + getCameraList() + "'" +
            "}";
    }
}
