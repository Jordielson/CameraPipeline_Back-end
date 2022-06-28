package com.camerapipeline.camera_pipeline.services.pdi;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.repository.pdi.ValueParameterRepository;

@Service
public class ValueParameterService {
    @Autowired
    private ValueParameterRepository repository;

    public ValueParameter saveValueParameter(ValueParameter valueParameter) {
        return repository.save(valueParameter);
    }

    public List<ValueParameter> getValueParameterList() {
        return repository.findAll();
    }

    public ValueParameter getValueParameter(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
    }

    public ValueParameter updateValueParameter(ValueParameter valueParameter) {
        return repository.save(valueParameter);
    }

    public void deleteValueParameter(ValueParameter valueParameter) {
        repository.delete(valueParameter);
    }
}
