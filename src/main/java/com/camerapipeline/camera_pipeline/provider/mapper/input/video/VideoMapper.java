package com.camerapipeline.camera_pipeline.provider.mapper.input.video;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.enums.PipelineInputType;
import com.camerapipeline.camera_pipeline.presentation.dto.input.video.VideoDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

@Component
public class VideoMapper extends Mapper<VideoData, VideoDTO> {
    @Autowired
    PipelineService pipelineService;

    @Override
    public VideoDTO toDTO(VideoData model) {
        VideoDTO dto = modelMapper.map(
            model, 
            VideoDTO.class
        );

        dto.setLocation(MvcUriComponentsBuilder
            .fromController(getClass())
            .path("/video/{id}")
            .buildAndExpand(dto.getId()).toString()
        );

        dto.setUrl(MvcUriComponentsBuilder
            .fromController(getClass())
            .path("/video/storage/{id}")
            .buildAndExpand(dto.getId()).toString()
        );

        dto.setType(PipelineInputType.VIDEO);

        dto.setPipelineId(
            (model.getPipeline() != null) 
            ? model.getPipeline().getId() 
            : null
        );
        
        return dto;
    }

    @Override
    public VideoData fromDTO(VideoDTO dto) {
        Converter<Integer, Pipeline> converterPipeline =
            ctx -> ctx.getSource() == null ? 
                null : 
                pipelineService.getById(
                    ctx.getSource()
                );
        TypeMap<VideoDTO, VideoData> typeMap = getTypeMap(
            VideoDTO.class, 
            VideoData.class
        );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterPipeline)
                    .map(
                        VideoDTO::getPipelineId, 
                        VideoData::setPipeline
                    );
            }
        );

        VideoData model = modelMapper.map(
            dto, 
            VideoData.class
        );
        return model;
    }
    
}
