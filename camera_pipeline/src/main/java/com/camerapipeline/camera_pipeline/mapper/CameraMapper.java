package com.camerapipeline.camera_pipeline.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.CameraDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.Camera;

@Component
public class CameraMapper extends Mapper<Camera, CameraDTO> {

    @Override
    public CameraDTO toDTO(Camera model) {
        return null;
    }

    @Override
    public Camera fromDTO(CameraDTO dto) {
        return null;
    }

    @Override
    public List<CameraDTO> toDTOList(List<Camera> modelList) {
        return null;
    }

    @Override
    public List<Camera> toModelList(List<CameraDTO> modelList) {
        return null;
    }
    
}
