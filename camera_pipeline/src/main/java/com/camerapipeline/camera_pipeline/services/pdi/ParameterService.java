package com.camerapipeline.camera_pipeline.services.pdi;

import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.pdi.Parameter;
import com.camerapipeline.camera_pipeline.repository.pdi.ParameterRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;

@Service
public class ParameterService extends ServiceAbstract<Parameter, Integer>{
    public ParameterService(ParameterRepository repository) {
        super(repository);
    }
}
