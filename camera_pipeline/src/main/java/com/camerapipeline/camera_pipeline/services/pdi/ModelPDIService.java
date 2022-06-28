package com.camerapipeline.camera_pipeline.services.pdi;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.ModelPDI;
import com.camerapipeline.camera_pipeline.model.Parameter;
import com.camerapipeline.camera_pipeline.repository.ModelPDIRepository;

@Service
public class ModelPDIService {
    @Autowired
    private ModelPDIRepository repository;
    @Autowired
    private ParameterService paramService;

    public ModelPDI saveModelPDI(ModelPDI modelPDI) {
        ModelPDI modelPDISaved = repository.save(modelPDI);
        for (Parameter param : modelPDI.getParameters()) {
            param.setModelPdi(modelPDISaved);
            paramService.saveParameter(param);
        }
        return modelPDISaved;
    }

    public List<ModelPDI> getModelPDIList() {
        return repository.findAll();
    }

    public ModelPDI getModelPDI(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
        
    }
    public ModelPDI getModelPDIByName(String name) {
        return repository.findByName(name)
            .orElseThrow(() -> new EntityNotFoundException(name));
        
    }

    public ModelPDI updateModelPDI(int id, ModelPDI modelPDI) {
        modelPDI.setId(id);
        ModelPDI modelPDIUpdate = repository.save(modelPDI);
        for (Parameter param : modelPDI.getParameters()) {
            param.setModelPdi(modelPDIUpdate);
            paramService.updateParameter(param);
        }
        return modelPDIUpdate;
    }

    public void deleteModelPDI(int id) {
        ModelPDI modelPDI = getModelPDI(id);
        for (Parameter param : modelPDI.getParameters()) {
            paramService.deleteParameter(param.getId());
        }
        repository.delete(modelPDI);
    }
}
