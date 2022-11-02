package com.camerapipeline.camera_pipeline.provider.services.pipeline;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.enums.DataHistoryEnum;
import com.camerapipeline.camera_pipeline.model.repository.pipeline.PipelineRepository;
import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.services.history.PdiDataHistoryService;
import com.camerapipeline.camera_pipeline.provider.services.history.PipelineDataHistoryService;
import com.camerapipeline.camera_pipeline.provider.services.pdi.PDIService;
import com.camerapipeline.camera_pipeline.provider.specification.pipeline.PipelineSpecification;

@Service
public class PipelineService extends ServiceAbstract<Pipeline, Integer>{
    @Autowired
    PipelineDataHistoryService historyService;
    @Autowired
    PdiDataHistoryService pdiHistoryService;
    @Autowired
    PDIService pdiService;

    public PipelineService(PipelineRepository repository) {
        super(repository);
    }

    @Transactional
    @Override
    public Pipeline create(Pipeline model, Principal principal) {
        Pipeline pipeline = super.create(model, principal);
        
        for (PDI pdi : model.getPDIList()) {
            pdi.setPipeline(pipeline);
            pdiService.create(pdi, principal);
        }

        historyService.register(DataHistoryEnum.INSERT, pipeline);

        return pipeline;
    }

    @Transactional
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

    @Transactional
    @Override
    public Pipeline delete(Integer id, Principal principal) {
        List<PDI> pdiList = pdiService.findByDigitalProcess(id);
        if (!pdiList.isEmpty()) {
            throw new BusinessException(
                "Pipeline is being used", 
                "PIPELINE_USED", 
                "Pipeline is being used by another pipeline"
            );
        }
        Pipeline pipe = super.delete(id, principal);
        historyService.register(DataHistoryEnum.DELETE, pipe);
        return pipe;
    }

    public Pipeline setActive(Integer id, Boolean active, Principal principal) {
        Pipeline pipeline = getById(id);
        pipeline.setActive(active);
        return update(id, pipeline, principal);
    }

    public boolean checkAdditionValidity(Integer parentPipelineID, Integer childPipelineID) {
        Pipeline addedPipeline = getById(childPipelineID);
        return checkAdditionValidity(parentPipelineID, addedPipeline.getPDIList());
    }

    public boolean checkAdditionValidity(Integer parentPipelineID, List<PDI> pdiList) {
        if(pdiList.size() == 0) {
            return true;
        }

        PDI pdi = pdiList.remove(0);
        if (pdi.getDigitalProcess().getId().equals(parentPipelineID)) {
            return false;
        }        

        if(pdi.getDigitalProcess() instanceof Pipeline) {
            boolean valid = checkAdditionValidity(parentPipelineID, ((Pipeline) pdi.getDigitalProcess()).getPDIList());
            if(valid == false) {
                return false;
            }
        }
        return checkAdditionValidity(parentPipelineID, pdiList);
    }

    @Override
    protected void beforeDelete(Pipeline model) {
        pdiHistoryService.deleteByDigitalProcess(model);
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
