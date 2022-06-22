package com.camerapipeline.camera_pipeline.services.pdi;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.PDI;
import com.camerapipeline.camera_pipeline.repository.PDIRepository;

@Service
public class PDIService {
    @Autowired
    private PDIRepository pdiRepository;

    public PDI savePDI(PDI pdi) {
        return pdiRepository.save(pdi);
    }

    public List<PDI> getPDIList() {
        return pdiRepository.findAll();
    }

    public PDI getPDI(Integer id) {
        return pdiRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
    }

    public PDI updatePDI(PDI pdi) {
        return pdiRepository.save(pdi);
    }

    public void deletePDI(PDI pdi) {
        pdiRepository.delete(pdi);
    }
}
