package com.camerapipeline.camera_pipeline.presentation.controller.camera;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
	public ResponseEntity<?> search(
			Principal principal,
			@RequestParam String name,
			Pageable pageable) {
        CameraDTO search = new CameraDTO().name(name);
        List<CameraDTO> list = mapper.toDTOList(
            service.search(
                pageable, 
                principal, 
                mapper.fromDTO(search)
            ).toList()
        );
		
		return new ResponseEntity<List<CameraDTO>>(list, HttpStatus.OK);
	}
}
