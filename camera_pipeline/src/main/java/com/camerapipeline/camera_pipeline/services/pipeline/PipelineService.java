package com.camerapipeline.camera_pipeline.services.pipeline;

import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.repository.pipeline.PipelineRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;

@Service
public class PipelineService extends ServiceAbstract<Pipeline, Integer>{
    public PipelineService(PipelineRepository repository) {
        super(repository);
    }
}
