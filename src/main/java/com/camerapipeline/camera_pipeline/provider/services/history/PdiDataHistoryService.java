package com.camerapipeline.camera_pipeline.provider.services.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.history.PdiDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.pdi.DigitalProcess;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.enums.DataHistoryEnum;
import com.camerapipeline.camera_pipeline.model.repository.history.PdiDataHistoryRepository;

@Service
public class PdiDataHistoryService {
    @Autowired
    PdiDataHistoryRepository repository;
    @Autowired
    ValueParameterDataHistoryService valueService;

    public PdiDataHistory register(DataHistoryEnum actions, PDI pdi, PipelineDataHistory pipeline) {
        PdiDataHistory data = PdiDataHistory.builder()
            .action(actions)
            .digitalProcess(pdi.getDigitalProcess())
            .pdiID(pdi.getId())
            .pipeline(pipeline)
            .index(pdi.getIndex())
            .build();
        
        repository.save(data);
        
        for (ValueParameter value : pdi.getValueParameters()) {
            value.setPdi(pdi);
            valueService.register(actions, value, data);
        }

        return data;
    }
    
    public void deleteByDigitalProcess(DigitalProcess process) {
        valueService.deleteByDigitalProcess(process);
        repository.deleteInBatch(process.getId());
    }
}
