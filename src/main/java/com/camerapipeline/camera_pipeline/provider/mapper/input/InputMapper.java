package com.camerapipeline.camera_pipeline.provider.mapper.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.input.image.ImageData;
import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;
import com.camerapipeline.camera_pipeline.presentation.dto.input.PipelineInputDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.input.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.input.image.ImageDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.input.video.VideoDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.mapper.input.camera.CameraMapper;
import com.camerapipeline.camera_pipeline.provider.mapper.input.image.ImageMapper;
import com.camerapipeline.camera_pipeline.provider.mapper.input.video.VideoMapper;

@Component
public class InputMapper extends Mapper<PipelineInput, PipelineInputDTO>{
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    CameraMapper cameraMapper;
    @Autowired
    VideoMapper videoMapper;

    @Override
    public PipelineInputDTO toDTO(PipelineInput model) {
        if (model instanceof Camera) {
            return cameraMapper.toDTO((Camera) model);
        } else if (model instanceof ImageData) {
            return imageMapper.toDTO((ImageData) model);
        } else {
            return videoMapper.toDTO((VideoData) model);
        }
    }

    @Override
    public PipelineInput fromDTO(PipelineInputDTO dto) {
        switch (dto.getType()) {
            case CAMERA:
                return cameraMapper.fromDTO((CameraDTO) dto);
            case IMAGE:
                return imageMapper.fromDTO((ImageDTO) dto);
            default:
                return videoMapper.fromDTO((VideoDTO) dto);
        }
    }
    
}
