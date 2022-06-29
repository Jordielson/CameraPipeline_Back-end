package com.camerapipeline.camera_pipeline.controller.pipeline;

import com.camerapipeline.camera_pipeline.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.dto.pipeline.PipelineDTO;
import com.camerapipeline.camera_pipeline.mapper.pipeline.PipelineMapper;
import com.camerapipeline.camera_pipeline.model.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.services.pipeline.PipelineService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pipeline")
public class PipelineController extends ControllerAbstract<Pipeline, PipelineDTO, Integer> {
    public PipelineController(PipelineService service, PipelineMapper mapper) {
        super(service, mapper);
    }
}
