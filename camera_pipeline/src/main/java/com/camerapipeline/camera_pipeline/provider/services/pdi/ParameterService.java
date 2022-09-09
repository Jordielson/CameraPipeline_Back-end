package com.camerapipeline.camera_pipeline.provider.services.pdi;

import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.model.repository.pdi.ParameterRepository;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;

@Service
public class ParameterService extends ServiceAbstract<Parameter, Integer>{
    public ParameterService(ParameterRepository repository) {
        super(repository);
    }
}
