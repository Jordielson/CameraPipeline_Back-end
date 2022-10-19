package com.camerapipeline.camera_pipeline.provider.services.history;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.enums.DataHistoryEnum;
import com.camerapipeline.camera_pipeline.model.repository.history.PipelineDataHistoryRepository;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.mapper.history.PipelineDataHistoryMapper;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;

@Service
public class PipelineDataHistoryService {
    @Autowired
    PipelineDataHistoryRepository repository;
    @Autowired
    PdiDataHistoryService pdiService;
    @Autowired
    PipelineDataHistoryMapper mapper;
    @Autowired
    AuthService authService;

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

    public Page<Pipeline> getHistoryByPipeline(Pageable pageable, Integer pipelineID, Principal principal) {
        return mapper.toDTOPage(
            repository.findAllByPipeline(
                pageable, 
                pipelineID,
                authService.loadUserByUsername(principal.getName()).getId()
            )
        );
    }

    public Pipeline getById(UUID id, Principal principal) {
        return mapper.toDTO(
            repository.findById(id)
            .orElseThrow(
                () -> new CustomEntityNotFoundException(
                    "PDI", 
                    id.toString()
                )
            )
        );
    }
}
