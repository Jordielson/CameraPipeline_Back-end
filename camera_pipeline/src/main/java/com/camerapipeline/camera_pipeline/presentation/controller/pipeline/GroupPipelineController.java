package com.camerapipeline.camera_pipeline.presentation.controller.pipeline;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.model.entities.pipeline.GroupPipeline;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.pipeline.GroupPipelineDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.pipeline.GroupPipelineMapper;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.GroupPipelineService;

@RestController
@RequestMapping("/group-pipeline")
public class GroupPipelineController extends ControllerAbstract<GroupPipeline, GroupPipelineDTO, Integer>{
    public GroupPipelineController(GroupPipelineService service,
        GroupPipelineMapper mapper) {
        super(service, mapper);
    }
}
