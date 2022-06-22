package com.camerapipeline.camera_pipeline.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.ParameterDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.Parameter;

@Component
public class ParameterMapper extends Mapper<Parameter, ParameterDTO>{

    public TypeMap<Parameter, ParameterDTO> getTypeMap() {
        return super.getTypeMap(
            Parameter.class, 
            ParameterDTO.class
        );
    }

    @Override
    public ParameterDTO toDTO(Parameter model) {
        TypeMap<Parameter, ParameterDTO> typeMap = getTypeMap();
        typeMap.addMapping(
            src -> src.getModelPdi().getId(), 
            ParameterDTO::setModelPdiId
        );
        ParameterDTO dto = modelMapper.map(
            model, 
            ParameterDTO.class
        );
        return dto;
    }

    @Override
    public Parameter fromDTO(ParameterDTO dto) {
        return null;
    }

    @Override
    public List<ParameterDTO> toDTOList(List<Parameter> modelList) {
        return new ArrayList<>();
    }

    @Override
    public List<Parameter> toModelList(List<ParameterDTO> modelList) {
        return null;
    }
    
}
