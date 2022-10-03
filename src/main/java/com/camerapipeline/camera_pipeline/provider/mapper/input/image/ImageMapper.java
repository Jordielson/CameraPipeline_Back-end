package com.camerapipeline.camera_pipeline.provider.mapper.input.image;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.model.entities.input.image.ImageData;
import com.camerapipeline.camera_pipeline.model.enums.PipelineInputType;
import com.camerapipeline.camera_pipeline.presentation.dto.input.image.ImageDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;

@Component
public class ImageMapper extends Mapper<ImageData, ImageDTO> {

    @Override
    public ImageDTO toDTO(ImageData model) {
        ImageDTO dto = modelMapper.map(
            model, 
            ImageDTO.class
        );

        dto.setLocation(MvcUriComponentsBuilder
            .fromController(getClass())
            .path("/image/{id}")
            .buildAndExpand(dto.getId()).toString()
        );

        dto.setUrl(MvcUriComponentsBuilder
            .fromController(getClass())
            .path("/image/storage/{id}")
            .buildAndExpand(dto.getId()).toString()
        );

        dto.setType(PipelineInputType.IMAGE);

        return dto;
    }

    @Override
    public ImageData fromDTO(ImageDTO dto) {
        ImageData model = modelMapper.map(
            dto, 
            ImageData.class
        );
        return model;
    }
    
}
