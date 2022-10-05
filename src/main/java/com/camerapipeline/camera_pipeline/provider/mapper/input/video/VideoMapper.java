package com.camerapipeline.camera_pipeline.provider.mapper.input.video;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;
import com.camerapipeline.camera_pipeline.model.enums.PipelineInputType;
import com.camerapipeline.camera_pipeline.presentation.dto.input.video.VideoDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;

@Component
public class VideoMapper extends Mapper<VideoData, VideoDTO> {

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
        
        return dto;
    }

    @Override
    public VideoData fromDTO(VideoDTO dto) {
        VideoData model = modelMapper.map(
            dto, 
            VideoData.class
        );
        return model;
    }
    
}
