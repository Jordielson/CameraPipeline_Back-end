package com.camerapipeline.camera_pipeline.presentation.dto.mosaic;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.camerapipeline.camera_pipeline.presentation.dto.input.camera.CameraDTO;

import lombok.Data;

@Data
public class MosaicDTO {
    private Integer id;

    @NotBlank
    private String name;

    private List<CameraDTO> cameraList;
}
