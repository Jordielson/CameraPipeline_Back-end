package com.camerapipeline.camera_pipeline.provider.mapper.input.camera;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.enums.PipelineInputType;
import com.camerapipeline.camera_pipeline.presentation.dto.input.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;

@Component
public class CameraMapper extends Mapper<Camera, CameraDTO> {

    @Override
    public CameraDTO toDTO(Camera model) {
        CameraDTO dto = modelMapper.map(
            model, 
            CameraDTO.class
        );

        dto.setLocation(MvcUriComponentsBuilder
            .fromController(getClass())
            .path("/camera/{id}")
            .buildAndExpand(dto.getId()).toString()
        );

        dto.setType(PipelineInputType.CAMERA);

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
