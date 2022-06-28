package com.camerapipeline.camera_pipeline.services.pdi;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.Parameter;
import com.camerapipeline.camera_pipeline.repository.ParameterRepository;

@Service
public class ParameterService {
    @Autowired
    private ParameterRepository repository;

    public Parameter saveParameter(Parameter parameter) {
        return repository.save(parameter);
    }

    public List<Parameter> getParameterList() {
        return repository.findAll();
    }

    public Parameter getParameter(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
    }

    public Parameter updateParameter(Parameter parameter) {
        return repository.save(parameter);
    }

    public void deleteParameter(int id) {
        repository.delete(getParameter(id));
    }
}
