package com.camerapipeline.camera_pipeline.provider.services.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.history.PdiDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.ValueParameterDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.enums.DataHistoryEnum;
import com.camerapipeline.camera_pipeline.model.repository.history.ValueParameterDataHistoryRepository;

@Service
public class ValueParameterDataHistoryService {
    @Autowired
    ValueParameterDataHistoryRepository repository;

    public ValueParameterDataHistory register(DataHistoryEnum actions, ValueParameter value, PdiDataHistory pdi) {
        ValueParameterDataHistory data = ValueParameterDataHistory.builder()
            .action(actions)
            .parameter(value.getParameter())
            .pdi(pdi)
            .value(value.getValue())
            .valueID(value.getId())
            .build();
        
        repository.save(data);
        return data;
    }

    public void removeByParameter(Parameter param) {
        repository.deleteByParameter(param.getId());
    }
}
