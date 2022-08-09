package com.camerapipeline.camera_pipeline.services.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.repository.pipeline.PipelineRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.services.pdi.PDIService;

@Service
public class PipelineService extends ServiceAbstract<Pipeline, Integer>{
    @Autowired
    PDIService pdiService;

    public PipelineService(PipelineRepository repository) {
        super(repository);
    }

    @Override
    public Pipeline create(Pipeline model) {
        for (PDI pdi : model.getPDIList()) {
            pdiService.create(pdi);
        }
        return super.create(model);
    }
}
