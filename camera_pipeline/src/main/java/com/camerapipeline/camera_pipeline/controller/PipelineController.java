package com.camerapipeline.camera_pipeline.controller;

import java.util.List;

import javax.validation.Valid;

import com.camerapipeline.camera_pipeline.dto.PipelineDTO;
import com.camerapipeline.camera_pipeline.mapper.PipelineMapper;
import com.camerapipeline.camera_pipeline.services.pipeline.PipelineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/list")
    public ResponseEntity<List<PipelineDTO>> getAll() {
        List<PipelineDTO> pipelines = mapper.toDTOList(
            pipelineService.getPipelineList()
        );
        return new ResponseEntity<List<PipelineDTO>>(pipelines, HttpStatus.OK);
    }
    
    @PostMapping("/save")
    public ResponseEntity<PipelineDTO> addPipeline(@Valid @RequestBody PipelineDTO dto) {
        PipelineDTO pipelineDTO = mapper.toDTO(
            pipelineService.savePipeline(
                mapper.fromDTO(dto)
            )
        );
        return new ResponseEntity<PipelineDTO>(pipelineDTO, HttpStatus.OK);
    }
}
