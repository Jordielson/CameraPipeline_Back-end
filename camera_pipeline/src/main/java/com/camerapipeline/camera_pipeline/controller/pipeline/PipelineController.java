package com.camerapipeline.camera_pipeline.controller.pipeline;

import java.util.List;

import javax.validation.Valid;

import com.camerapipeline.camera_pipeline.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.mapper.pipeline.PipelineMapper;
import com.camerapipeline.camera_pipeline.services.pipeline.PipelineService;

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

@RestController
@RequestMapping("/pipeline")
public class PipelineController {
    @Autowired
    PipelineService pipelineService;
    @Autowired
    PipelineMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<PipelineDTO>> getAll(Pageable pageable) {
        List<PipelineDTO> pipelines = mapper.toDTOList(
            pipelineService.getAll(pageable).toList()
        );
        return new ResponseEntity<List<PipelineDTO>>(pipelines, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PipelineDTO> getPipeline(@PathVariable("id") Integer id) {
        PipelineDTO dto = mapper.toDTO(
            pipelineService.getById(id)
        );
        return new ResponseEntity<PipelineDTO>(dto, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<PipelineDTO> addPipeline(@Valid @RequestBody PipelineDTO dto) {
        PipelineDTO pipelineDTO = mapper.toDTO(
            pipelineService.create(
                mapper.fromDTO(dto)
            )
        );
        return new ResponseEntity<PipelineDTO>(pipelineDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PipelineDTO> updatePipeline(@PathVariable("id") Integer id, @Valid @RequestBody PipelineDTO dto) {
        PipelineDTO pipelineDTO = mapper.toDTO(
            pipelineService.update(
                id,
                mapper.fromDTO(dto)
            )
        );
        return new ResponseEntity<PipelineDTO>(
            pipelineDTO,
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePipeline(@PathVariable("id") Integer id) {
        pipelineService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
