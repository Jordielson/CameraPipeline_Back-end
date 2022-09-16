package com.camerapipeline.camera_pipeline.provider.services.pdi;

import java.security.Principal;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.repository.pdi.ModelPDIRepository;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.pdi.ModelPDISpecification;

@Service
public class ModelPDIService extends ServiceAbstract<ModelPDI, Integer> {
    @Autowired
    private ParameterService paramService;
    
    public ModelPDIService(ModelPDIRepository repository) {
        super(repository);
    }

    @Override
    public ModelPDI create(ModelPDI model, Principal principal) {
        model.setUser(super.authService.loadUserByUsername(principal.getName()));
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
    public ModelPDI update(Integer id, ModelPDI model, Principal principal) {
        model.setUser(super.authService.loadUserByUsername(principal.getName()));
        
        ModelPDI oldModelPDI = this.repository.findById(id)
                .map(existing -> existing
                ).orElseThrow(() -> new EntityNotFoundException(id.toString()));
                
        model.setId(id);
        for (Parameter param : model.getParameters()) {
            param.setModelPdi(model);
            if(param.getId() != null && param.getId() != 0 &&
               id.equals(paramService.getById(param.getId()).getModelPdi().getId())) {
                paramService.update(param.getId(), param, principal);
            } else {
                paramService.create(param);
            }
        }

        oldModelPDI.getParameters().forEach(oldParam -> {
            if(!model.getParameters().contains(oldParam)) {
                paramService.delete(oldParam.getId(), principal);
            }
        });

        return super.repository.save(model);
    }

    @Override
    public ModelPDI delete(Integer id, Principal principal) {
        ModelPDI modelPDI = getById(id);
        for (Parameter param : modelPDI.getParameters()) {
            paramService.delete(param.getId(), principal);
        }
        return super.delete(id, principal);
    }

    @Override
    protected Specification<ModelPDI> getSpecification(ModelPDI search) {
        return new ModelPDISpecification(search);
    }

    public boolean checkValidName(String name, Integer id, Principal principal) {
        User u = getUserByPrincipal(principal);
        Optional<ModelPDI> modelPdiOptional 
            = ((ModelPDIRepository) repository).findByName(name, u.getId());
        return (modelPdiOptional.isPresent()
            && modelPdiOptional.get().getId() != id) 
            ? false : true;
    }
}
