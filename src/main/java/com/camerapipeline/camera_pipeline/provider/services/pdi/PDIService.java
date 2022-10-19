package com.camerapipeline.camera_pipeline.provider.services.pdi;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.repository.pdi.PDIRepository;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.ServiceAbstract;
import com.camerapipeline.camera_pipeline.provider.specification.pdi.PDISpecification;

@Service
public class PDIService extends ServiceAbstract<PDI, Integer> {
    @Autowired
    ValueParameterService valueService;
    
    public PDIService(PDIRepository repository) {
        super(repository);
    }
    
    @Override
    public PDI create(PDI model) {
        PDI pdi = super.create(model);
        for (ValueParameter value : model.getValueParameters()) {
            value.setPdi(pdi);
            valueService.create(value);
        }
        return pdi;
    }

    @Override
    public PDI update(Integer id, PDI model, Principal principal) {
        PDI oldPDI = this.repository.findById(id)
                .map(existing -> existing
                ).orElseThrow(() -> new EntityNotFoundException(id.toString()));
                
        model.setId(id);
        for (ValueParameter value : model.getValueParameters()) {
            value.setPdi(model);
            if(value.getId() != null && value.getId() > 0) {
                valueService.update(value.getId(), value, principal);
            } else {
                valueService.create(value);
            }
        }

        oldPDI.getValueParameters().forEach(oldParam -> {
            if(!model.getValueParameters().contains(oldParam)) {
                valueService.delete(oldParam.getId(), principal);
            }
        });

        return super.update(id, model, principal);
    }

    @Override
    public PDI delete(Integer id, Principal principal) {
        return super.delete(id, principal);
    }

    @Override
    protected Specification<PDI> getSpecification(PDI search) {
        return new PDISpecification(search);
    }

    @Override
    protected EntityNotFoundException throwNotFoundEntity(Integer id) {
        return new CustomEntityNotFoundException("PDI", id.toString());
    }
}
