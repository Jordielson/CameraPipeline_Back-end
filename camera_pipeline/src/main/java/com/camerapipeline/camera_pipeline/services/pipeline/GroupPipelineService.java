package com.camerapipeline.camera_pipeline.services.pipeline;

import com.camerapipeline.camera_pipeline.model.pipeline.GroupPipeline;
import com.camerapipeline.camera_pipeline.repository.pipeline.GroupPipelineRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;

import org.springframework.stereotype.Service;

@Service
public class GroupPipelineService extends ServiceAbstract<GroupPipeline, Integer> {
    public GroupPipelineService(GroupPipelineRepository repository) {
        super(repository);
    }
}
