package com.camerapipeline.camera_pipeline.services.pdi;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.repository.pdi.PDIRepository;

@Service
public class PDIService {
    @Autowired
    private PDIRepository pdiRepository;
    @Autowired
    ValueParameterService valueService;

    public PDI savePDI(PDI pdi) {
        PDI pdiSaved = pdiRepository.save(pdi);
        for (ValueParameter value : pdi.getValueParameters()) {
            value.setPdi(pdiSaved);
            valueService.saveValueParameter(value);
        }
        return pdiSaved;
    }

    public List<PDI> getPDIList() {
        return pdiRepository.findAll();
    }

    public PDI getPDI(Integer id) {
        return pdiRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
    }

    public PDI updatePDI(int id, PDI pdi) {
        for (ValueParameter value : pdi.getValueParameters()) {
            value.setPdi(pdi);
            valueService.updateValueParameter(value);
        }
        PDI pdiUpdated = pdiRepository.save(pdi);
        return pdiUpdated;
    }

    public void deletePDI(int id) {
        for (ValueParameter value : getPDI(id).getValueParameters()) {
            valueService.deleteValueParameter(value);
        }
        pdiRepository.delete(getPDI(id));
    }
}
