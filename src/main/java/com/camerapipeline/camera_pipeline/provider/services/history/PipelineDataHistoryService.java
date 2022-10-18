package com.camerapipeline.camera_pipeline.provider.services.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.enums.DataHistoryEnum;
import com.camerapipeline.camera_pipeline.model.repository.history.PipelineDataHistoryRepository;

@Service
public class PipelineDataHistoryService {
    @Autowired
    PipelineDataHistoryRepository repository;
    @Autowired
    PdiDataHistoryService pdiService;

    public PipelineDataHistory register(DataHistoryEnum actions, Pipeline pipeline) {
        PipelineDataHistory pipelineData = PipelineDataHistory.builder()
            .action(actions)
            .description(pipeline.getDescription())
            .isActive(pipeline.isActive())
            .name(pipeline.getName())
            .pipelineID(pipeline.getId())
            .user(pipeline.getUser())
            .build();
        
        pipelineData = repository.save(pipelineData);

        for (PDI pdi : pipeline.getPDIList()) {
            pdi.setPipeline(pipeline);
            pdiService.register(actions, pdi, pipelineData);
        }

        return pipelineData;
    }
}
