package com.camerapipeline.camera_pipeline.provider.mapper.input.image;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.model.entities.input.image.ImageData;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.enums.PipelineInputType;
import com.camerapipeline.camera_pipeline.presentation.dto.input.image.ImageDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

@Component
public class ImageMapper extends Mapper<ImageData, ImageDTO> {
    @Autowired
    PipelineService pipelineService;
    
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

        dto.setPipelineId(
            (model.getPipeline() != null) 
            ? model.getPipeline().getId() 
            : null
        );

        return dto;
    }

    @Override
    public ImageData fromDTO(ImageDTO dto) {
        Converter<Integer, Pipeline> converterPipeline =
            ctx -> ctx.getSource() == null ? 
                null : 
                pipelineService.getById(
                    ctx.getSource()
                );
        TypeMap<ImageDTO, ImageData> typeMap = getTypeMap(
            ImageDTO.class, 
            ImageData.class
        );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterPipeline)
                    .map(
                        ImageDTO::getPipelineId, 
                        ImageData::setPipeline
                    );
            }
        );

        ImageData model = modelMapper.map(
            dto, 
            ImageData.class
        );
        return model;
    }
    
}
