package com.camerapipeline.camera_pipeline.provider.mapper.input.camera;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.enums.PipelineInputType;
import com.camerapipeline.camera_pipeline.presentation.dto.input.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

@Component
public class CameraMapper extends Mapper<Camera, CameraDTO> {
    @Autowired
    PipelineService pipelineService;

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

        dto.setPipelineId(
            (model.getPipeline() != null) 
            ? model.getPipeline().getId() 
            : null
        );
        
        return dto;
    }

    @Override
    public Camera fromDTO(CameraDTO dto) {
        Converter<Integer, Pipeline> converterPipeline =
            ctx -> ctx.getSource() == null ? 
                null : 
                pipelineService.getById(
                    ctx.getSource()
                );
        TypeMap<CameraDTO, Camera> typeMap = getTypeMap(
            CameraDTO.class, 
            Camera.class
        );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterPipeline)
                    .map(
                        CameraDTO::getPipelineId, 
                        Camera::setPipeline
                    );
            }
        );
        Camera model = modelMapper.map(
            dto, 
            Camera.class
        );
        return model;
    }
}
