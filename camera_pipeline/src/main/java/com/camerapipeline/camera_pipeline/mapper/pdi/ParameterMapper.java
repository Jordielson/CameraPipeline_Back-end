package com.camerapipeline.camera_pipeline.mapper.pdi;

import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.pdi.ParameterDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.pdi.Parameter;

@Component
public class ParameterMapper extends Mapper<Parameter, ParameterDTO>{
    @Override
    public ParameterDTO toDTO(Parameter model) {
        ParameterDTO dto = modelMapper.map(
            model, 
            ParameterDTO.class
        );
        return dto;
    }

    @Override
    public Parameter fromDTO(ParameterDTO dto) {
        Parameter parameter = modelMapper.map(
            dto, 
            Parameter.class
        );
        return parameter;
    }
}
