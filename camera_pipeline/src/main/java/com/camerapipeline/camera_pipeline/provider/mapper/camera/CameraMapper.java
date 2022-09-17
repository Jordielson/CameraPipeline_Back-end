package com.camerapipeline.camera_pipeline.provider.mapper.camera;

import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.presentation.dto.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;

@Component
public class CameraMapper extends Mapper<Camera, CameraDTO> {

    @Override
    public CameraDTO toDTO(Camera model) {
        CameraDTO dto = modelMapper.map(
            model, 
            CameraDTO.class
        );
        return dto;
    }

    @Override
    public Camera fromDTO(CameraDTO dto) {
        Camera model = modelMapper.map(
            dto, 
            Camera.class
        );
        return model;
    }
}
