package com.camerapipeline.camera_pipeline.provider.services.pdi;

import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.repository.pdi.ValueParameterRepository;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;

@Service
public class ValueParameterService extends ServiceAbstract<ValueParameter, Integer> {
    public ValueParameterService(ValueParameterRepository repository) {
        super(repository);
    }    
}
