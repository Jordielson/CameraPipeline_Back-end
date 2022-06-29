package com.camerapipeline.camera_pipeline.controller.camera;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.dto.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.mapper.camera.CameraMapper;
import com.camerapipeline.camera_pipeline.services.camera.CameraService;

@RestController
@RequestMapping("/camera")
public class CameraController {
    @Autowired
    CameraService cameraService;
    @Autowired
    CameraMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<CameraDTO>> getAll(Pageable pageable) {
        List<CameraDTO> list = mapper.toDTOList(
            cameraService.getAll(pageable).toList()
        );
        return new ResponseEntity<List<CameraDTO>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CameraDTO> get(@PathVariable("id") Integer id) {
        CameraDTO dto = mapper.toDTO(
            cameraService.getById(id)
        );
        return new ResponseEntity<CameraDTO>(dto, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<CameraDTO> add(@Valid @RequestBody CameraDTO dto, Principal principal) {
        CameraDTO groupDTO = mapper.toDTO(
            cameraService.create(
                mapper.fromDTO(dto), 
                principal
            )
        );
        return new ResponseEntity<CameraDTO>(groupDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CameraDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody CameraDTO dto, Principal principal) {
        CameraDTO groupDTO = mapper.toDTO(
            cameraService.update(
                id,
                mapper.fromDTO(dto), 
                principal
            )
        );
        return new ResponseEntity<CameraDTO>(
            groupDTO,
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        cameraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
