package com.camerapipeline.camera_pipeline.services.pdi;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.pdi.Parameter;
import com.camerapipeline.camera_pipeline.repository.pdi.ModelPDIRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;

@Service
public class ModelPDIService extends ServiceAbstract<ModelPDI, Integer> {
    @Autowired
    private ParameterService paramService;
    
    public ModelPDIService(ModelPDIRepository repository) {
        super(repository);
    }

    @Override
    public ModelPDI create(ModelPDI model) {
        ModelPDI modelPDI = super.create(model);
        for (Parameter param : model.getParameters()) {
            param.setModelPdi(modelPDI);
            paramService.create(param);
        }
        return modelPDI;
    }

    public ModelPDI getByName(String name) {
        return ((ModelPDIRepository) repository).findByName(name)
            .orElseThrow(() -> new EntityNotFoundException(name));
    }

    @Override
    public ModelPDI update(Integer id, ModelPDI model) {
        ModelPDI oldModelPDI = this.repository.findById(id)
                .map(existing -> existing
                ).orElseThrow(() -> new EntityNotFoundException(id.toString()));
                
        model.setId(id);
        for (Parameter param : model.getParameters()) {
            param.setModelPdi(model);
            if(param.getId() != null && param.getId() != 0 &&
               id.equals(paramService.getById(param.getId()).getModelPdi().getId())) {
                paramService.update(param.getId(), param);
            } else {
                paramService.create(param);
            }
        }

        oldModelPDI.getParameters().forEach(oldParam -> {
            if(!model.getParameters().contains(oldParam)) {
                paramService.delete(oldParam.getId());
            }
        });

        return super.repository.save(model);
    }

    @Override
    public ModelPDI delete(Integer id) {
        ModelPDI modelPDI = getById(id);
        for (Parameter param : modelPDI.getParameters()) {
            paramService.delete(param.getId());
        }
        return super.delete(id);
    }
}
