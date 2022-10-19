package com.camerapipeline.camera_pipeline.provider.services.pipeline;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.enums.DataHistoryEnum;
import com.camerapipeline.camera_pipeline.model.repository.pipeline.PipelineRepository;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.services.history.PipelineDataHistoryService;
import com.camerapipeline.camera_pipeline.provider.services.pdi.PDIService;
import com.camerapipeline.camera_pipeline.provider.specification.pipeline.PipelineSpecification;

@Service
public class PipelineService extends ServiceAbstract<Pipeline, Integer>{
    @Autowired
    PipelineDataHistoryService historyService;
    @Autowired
    PDIService pdiService;

    public PipelineService(PipelineRepository repository) {
        super(repository);
    }

    @Override
    public Pipeline create(Pipeline model) {
        Pipeline pipeline = super.create(model);
        
        for (PDI pdi : model.getPDIList()) {
            pdi.setPipeline(pipeline);
            pdiService.create(pdi);
        }

        historyService.register(DataHistoryEnum.INSERT, pipeline);

        return pipeline;
    }

    @Override
    public Pipeline update(Integer id, Pipeline model, Principal principal) {
        Pipeline oldPipeline = this.repository.findById(id)
                .map(existing -> existing
                ).orElseThrow(() -> new EntityNotFoundException(id.toString()));
            
        for (PDI pdi : model.getPDIList()) {
            pdi.setPipeline(oldPipeline);
            if(pdi.getId() != null && pdi.getId() != 0) {
                pdiService.update(pdi.getId(), pdi, principal);
            } else {
                pdiService.create(pdi);
            }
        }

        oldPipeline.getPDIList().forEach(oldPdi -> {
            if(!model.getPDIList().contains(oldPdi)) {
                pdiService.delete(oldPdi.getId(), principal);
            }
        });

        Pipeline pipeline = super.update(id, model, principal);

        historyService.register(DataHistoryEnum.UPDATE, pipeline);

        return pipeline;
    }

    @Override
    protected Specification<Pipeline> getSpecification(Pipeline search) {
        return new PipelineSpecification(search);
    }

    @Override
    protected EntityNotFoundException throwNotFoundEntity(Integer id) {
        return new CustomEntityNotFoundException("Pipeline", id.toString());
    }
}
