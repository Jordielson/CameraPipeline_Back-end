package com.camerapipeline.camera_pipeline.provider.services.pdi;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.repository.pdi.ValueParameterRepository;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.pdi.ValueParameterSpecification;

@Service
public class ValueParameterService extends ServiceAbstract<ValueParameter, Integer> {
    public ValueParameterService(ValueParameterRepository repository) {
        super(repository);
    }

    @Override
    protected Specification<ValueParameter> getSpecification(ValueParameter search) {
        return new ValueParameterSpecification(search);
    }    
    
    @Override
    protected EntityNotFoundException throwNotFoundEntity(Integer id) {
        return new CustomEntityNotFoundException("Value Parameter", id.toString());
    }
}
