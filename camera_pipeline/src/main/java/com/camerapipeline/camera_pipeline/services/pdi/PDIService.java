package com.camerapipeline.camera_pipeline.services.pdi;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.repository.pdi.PDIRepository;
import com.camerapipeline.camera_pipeline.services.ServiceAbstract;

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
    public PDI update(Integer id, PDI model) {
        PDI oldPDI = this.repository.findById(id)
                .map(existing -> existing
                ).orElseThrow(() -> new EntityNotFoundException(id.toString()));
                
        model.setId(id);
        for (ValueParameter value : model.getValueParameters()) {
            value.setPdi(model);
            if(value.getId() != null && value.getId() != 0 &&
               id.equals(valueService.getById(value.getId()).getPdi().getId())) {
                valueService.update(value.getId(), value);
            } else {
                valueService.create(value);
            }
        }

        oldPDI.getValueParameters().forEach(oldParam -> {
            if(!model.getValueParameters().contains(oldParam)) {
                valueService.delete(oldParam.getId());
            }
        });

        return super.repository.save(model);
    }

    @Override
    public PDI delete(Integer id) {
        for (ValueParameter value : getById(id).getValueParameters()) {
            valueService.delete(value.getId());
        }
        return super.delete(id);
    }
}
