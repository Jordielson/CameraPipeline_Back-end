package com.camerapipeline.camera_pipeline.provider.mapper.input.video;

import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;
import com.camerapipeline.camera_pipeline.presentation.dto.input.video.VideoDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;

@Component
public class VideoMapper extends Mapper<VideoData, VideoDTO> {

    @Override
    public VideoDTO toDTO(VideoData model) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VideoData fromDTO(VideoDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
