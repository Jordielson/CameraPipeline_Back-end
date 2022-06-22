package com.camerapipeline.camera_pipeline.services.pdi;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.ModelPDI;
import com.camerapipeline.camera_pipeline.repository.ModelPDIRepository;

@Service
public class ModelPDIService {
    @Autowired
    private ModelPDIRepository repository;

    public ModelPDI saveModelPDI(ModelPDI modelPDI) {
        return repository.save(modelPDI);
    }

    public List<ModelPDI> getModelPDIList() {
        return repository.findAll();
    }

    public ModelPDI getModelPDI(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
        
    }

    public ModelPDI updateModelPDI(ModelPDI modelPDI) {
        return repository.save(modelPDI);
    }

    public void deleteModelPDI(ModelPDI modelPDI) {
        repository.delete(modelPDI);
    }
}
