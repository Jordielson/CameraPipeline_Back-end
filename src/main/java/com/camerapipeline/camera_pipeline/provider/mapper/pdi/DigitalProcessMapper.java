package com.camerapipeline.camera_pipeline.provider.mapper.pdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.pdi.DigitalProcess;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.enums.Category;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.DigitalProcessDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.services.pdi.ModelPDIService;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

@Component
public class DigitalProcessMapper extends Mapper<DigitalProcess, DigitalProcessDTO>{
    @Autowired
    PipelineService pipelineService;
    @Autowired
    ModelPDIService modelService;

    @Override
    public DigitalProcessDTO toDTO(DigitalProcess model) {
        DigitalProcessDTO dto = modelMapper.map(
            model, 
            DigitalProcessDTO.class
        );
        if(model instanceof ModelPDI) {
            dto.setCategory(Category.MODEL_PDI);
        } else {
            dto.setCategory(Category.PIPELINE);
        }
        return dto;
    }

    @Override
    public DigitalProcess fromDTO(DigitalProcessDTO dto) {
        switch (dto.getCategory()) {
            case MODEL_PDI:
                return modelService.getById(dto.getId());
            case PIPELINE:
                return pipelineService.getById(dto.getId());
            default:
                return null;
        }
    }
    
}
