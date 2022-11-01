package com.camerapipeline.camera_pipeline.provider.services.history;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.enums.DataHistoryEnum;
import com.camerapipeline.camera_pipeline.model.repository.history.PipelineDataHistoryRepository;
import com.camerapipeline.camera_pipeline.model.repository.pdi.PDIRepository;
import com.camerapipeline.camera_pipeline.model.repository.pdi.ValueParameterRepository;
import com.camerapipeline.camera_pipeline.model.repository.pipeline.PipelineRepository;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;

@Service
public class PipelineDataHistoryService {
    @Autowired
    PipelineDataHistoryRepository repository;
    @Autowired
    PdiDataHistoryService pdiHistoryService;
    @Autowired
    AuthService authService;
    @Autowired
    PipelineRepository pipelineRepository;
    @Autowired
    PDIRepository pdiRepository;
    @Autowired
    ValueParameterRepository valueRepository;

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
            pdiHistoryService.register(actions, pdi, pipelineData);
        }

        return pipelineData;
    }

    public Page<PipelineDataHistory> getHistoryByPipeline(Pageable pageable, Integer pipelineID, Principal principal) {
        return repository.findAllByPipeline(
            pageable, 
            pipelineID,
            authService.loadUserByUsername(principal.getName()).getId()
        );
    }

    public PipelineDataHistory getById(UUID id, Principal principal) {
        return repository.findById(id)
            .orElseThrow(
            () -> new CustomEntityNotFoundException(
                "PDI", 
                id.toString()
            )
        );
    }

    @Transactional
    public Pipeline restore(Pipeline pipeline) {
        pipelineRepository.findById(pipeline.getId()).map(existing -> {
            for (PDI pdi : existing.getPDIList()) {
                pdiRepository.delete(pdi);
            }
            return existing;
        });
        valueRepository.flush();
        pdiRepository.flush();
        
        for (PDI pdi : pipeline.getPDIList()) {
            pdi.setId(null);
            pdiRepository.save(pdi);

            for (ValueParameter value : pdi.getValueParameters()) {
                value.setPdi(pdi);
                valueRepository.save(value);
            }
        }
        return pipelineRepository.save(pipeline);
    }


    public PipelineDataHistory renameVersion(UUID id, String name, Principal principal) {
        PipelineDataHistory version = getById(id, principal);
        version.setVersionName(name);
        return repository.save(version);
    }

    public void cleanUserHistory(Integer userId) {
        List<PipelineDataHistory> list = repository.findByUser(userId);

        for (PipelineDataHistory pipelineDataHistory : list) {
            repository.delete(pipelineDataHistory);
        }
    }
}
