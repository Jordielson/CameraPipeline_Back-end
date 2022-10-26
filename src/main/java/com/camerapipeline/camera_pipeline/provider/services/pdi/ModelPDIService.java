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
import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.services.history.PdiDataHistoryService;
import com.camerapipeline.camera_pipeline.provider.specification.pdi.ModelPDISpecification;

@Service
public class ModelPDIService extends ServiceAbstract<ModelPDI, Integer> {
    @Autowired
    private ParameterService paramService;

    @Autowired
    PdiDataHistoryService pdiHistoryService;
    
    public ModelPDIService(ModelPDIRepository repository) {
        super(repository);
    }

    @Override
    public ModelPDI create(ModelPDI model, Principal principal) {
        validModelPDI(model, null, principal);

        model.setUser(getUserByPrincipal(principal));
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
        validModelPDI(model, id, principal);

        model.setUser(getUserByPrincipal(principal));
        
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
    protected void beforeDelete(ModelPDI model) {
        pdiHistoryService.deleteByDigitalProcess(model);
    }

    @Override
    protected Specification<ModelPDI> getSpecification(ModelPDI search) {
        return new ModelPDISpecification(search);
    }

    private void validModelPDI(ModelPDI model, Integer id, Principal principal) {
        if (!checkValidName(model.getName(), id, principal)) {
            throw new BusinessException(String.format("There is already a PDI model with the name %s", model.getName()));
        }else if (!checkValidUrl(model.getURL(), id, principal)) {
            throw new BusinessException(String.format("There is already a PDI model with the url %s", model.getURL()));
        }
    }

    public boolean checkValidName(String name, Integer id, Principal principal) {
        User u = getUserByPrincipal(principal);
        Optional<ModelPDI> modelPdiOptional 
            = ((ModelPDIRepository) repository).findByName(name, u.getId());
        return (modelPdiOptional.isPresent()
            && modelPdiOptional.get().getId() != id) 
            ? false : true;
    }

    public boolean checkValidUrl(String url, Integer id, Principal p) {
        Optional<ModelPDI> modelPdiOptional 
            = ((ModelPDIRepository) repository).findByURL(
                url, 
                getUserByPrincipal(p).getId()
            );
        return (modelPdiOptional.isPresent()
            && modelPdiOptional.get().getId() != id) 
            ? false : true;
    }

    public boolean checkIfItUsed(Integer id, Principal principal) {
        ModelPDI modelPdi = getById(id, principal);
        return !modelPdi.getPdiList().isEmpty();
    }

    @Override
    protected EntityNotFoundException throwNotFoundEntity(Integer id) {
        return new CustomEntityNotFoundException("Model PDI", id.toString());
    }
}
