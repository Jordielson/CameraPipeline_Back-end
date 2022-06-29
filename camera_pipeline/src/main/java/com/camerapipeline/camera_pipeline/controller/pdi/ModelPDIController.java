package com.camerapipeline.camera_pipeline.controller.pdi;

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

import com.camerapipeline.camera_pipeline.dto.pdi.ModelPdiDTO;
import com.camerapipeline.camera_pipeline.mapper.pdi.ModelPDIMapper;
import com.camerapipeline.camera_pipeline.services.pdi.ModelPDIService;


@RestController
@RequestMapping("/model-pdi")
public class ModelPDIController {
    @Autowired
    ModelPDIService modelPDIService;
    @Autowired
    ModelPDIMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<ModelPdiDTO>> getAll(Pageable pageable) {
        List<ModelPdiDTO> list = mapper.toDTOList(
            modelPDIService.getAll(pageable).toList()
        );
        return new ResponseEntity<List<ModelPdiDTO>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ModelPdiDTO> get(@PathVariable("id") Integer id) {
        ModelPdiDTO dto = mapper.toDTO(
            modelPDIService.getById(id)
        );
        return new ResponseEntity<ModelPdiDTO>(dto, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<ModelPdiDTO> add(@Valid @RequestBody ModelPdiDTO dto) {
        ModelPdiDTO groupDTO = mapper.toDTO(
            modelPDIService.create(
                mapper.fromDTO(dto)
            )
        );
        return new ResponseEntity<ModelPdiDTO>(groupDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelPdiDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody ModelPdiDTO dto) {
        ModelPdiDTO groupDTO = mapper.toDTO(
            modelPDIService.update(
                id,
                mapper.fromDTO(dto)
            )
        );
        return new ResponseEntity<ModelPdiDTO>(
            groupDTO,
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        modelPDIService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
