package com.camerapipeline.camera_pipeline.provider.services.pipeline;

import com.camerapipeline.camera_pipeline.model.entities.pipeline.GroupPipeline;
import com.camerapipeline.camera_pipeline.model.repository.pipeline.GroupPipelineRepository;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;

import org.springframework.stereotype.Service;

@Service
public class GroupPipelineService extends ServiceAbstract<GroupPipeline, Integer> {
    public GroupPipelineService(GroupPipelineRepository repository) {
        super(repository);
    }
}
