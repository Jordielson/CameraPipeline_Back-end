package com.camerapipeline.camera_pipeline.controller.pipeline;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.dto.pipeline.GroupPipelineDTO;
import com.camerapipeline.camera_pipeline.mapper.pipeline.GroupPipelineMapper;
import com.camerapipeline.camera_pipeline.model.pipeline.GroupPipeline;
import com.camerapipeline.camera_pipeline.services.pipeline.GroupPipelineService;

@RestController
@RequestMapping("/group-pipeline")
public class GroupPipelineController extends ControllerAbstract<GroupPipeline, GroupPipelineDTO, Integer>{
    public GroupPipelineController(GroupPipelineService service,
        GroupPipelineMapper mapper) {
        super(service, mapper);
    }
}
