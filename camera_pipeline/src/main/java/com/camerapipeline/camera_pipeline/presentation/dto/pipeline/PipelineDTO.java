package com.camerapipeline.camera_pipeline.presentation.dto.pipeline;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<PdiDTO> PDIList;
    private List<CameraDTO> cameraList;

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
        setActive(isActive);
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
}