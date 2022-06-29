package com.camerapipeline.camera_pipeline.services.pdi;

import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.repository.pdi.ValueParameterRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;

@Service
public class ValueParameterService extends ServiceAbstract<ValueParameter, Integer> {
    public ValueParameterService(ValueParameterRepository repository) {
        super(repository);
    }    
}
