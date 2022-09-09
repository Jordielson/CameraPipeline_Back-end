package com.camerapipeline.camera_pipeline.presentation.controller.camera;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.services.camera.CameraService;

@RestController
@RequestMapping("/camera")
public class CameraController extends ControllerAbstract<Camera, CameraDTO, Integer>{
    public CameraController(CameraService service, Mapper<Camera, CameraDTO> mapper) {
        super(service, mapper);
    }
}
