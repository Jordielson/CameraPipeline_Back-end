package com.camerapipeline.camera_pipeline.provider.services.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.repository.pipeline.PipelineRepository;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.services.pdi.PDIService;
import com.camerapipeline.camera_pipeline.provider.specification.pipeline.PipelineSpecification;

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

    @Override
    protected Specification<Pipeline> getSpecification(Pipeline search) {
        return new PipelineSpecification(search);
    }
}
