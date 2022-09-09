package com.camerapipeline.camera_pipeline.presentation.controller.pipeline;

import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.pipeline.PipelineMapper;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pipeline")
public class PipelineController extends ControllerAbstract<Pipeline, PipelineDTO, Integer> {
    public PipelineController(PipelineService service, PipelineMapper mapper) {
        super(service, mapper);
    }

    @PostMapping("/preview/{id}")
    public ResponseEntity<String> execute(@PathVariable("id") Integer id, Principal principal) {
        return new ResponseEntity<String>("rtsp://rtsp.stream/pattern", HttpStatus.OK);
    }
}
