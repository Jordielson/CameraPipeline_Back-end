package com.camerapipeline.camera_pipeline.provider.services.pdi;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.model.repository.pdi.ParameterRepository;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.pdi.ParameterSpecification;

@Service
public class ParameterService extends ServiceAbstract<Parameter, Integer>{
    public ParameterService(ParameterRepository repository) {
        super(repository);
    }

    @Override
    protected Specification<Parameter> getSpecification(Parameter search) {
        return new ParameterSpecification(search);
    }
    
    @Override
    protected EntityNotFoundException throwNotFoundEntity(Integer id) {
        return new CustomEntityNotFoundException("Parameter", id.toString());
    }
}
