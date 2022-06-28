package com.camerapipeline.camera_pipeline.mapper.camera;

import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.camera.Camera;

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
