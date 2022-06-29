package com.camerapipeline.camera_pipeline.mapper.pdi;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.pdi.ParameterDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.pdi.Parameter;
import com.camerapipeline.camera_pipeline.services.pdi.ModelPDIService;

@Component
public class ParameterMapper extends Mapper<Parameter, ParameterDTO>{
    @Autowired
    ModelPDIService modelService;

    @Override
    public ParameterDTO toDTO(Parameter model) {
        TypeMap<Parameter, ParameterDTO> typeMap = getTypeMap(
            Parameter.class, 
            ParameterDTO.class
        );
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
        TypeMap<ParameterDTO, Parameter> typeMap = getTypeMap(
            ParameterDTO.class, 
            Parameter.class
        );
        Converter<Integer, ModelPDI> converter =
            ctx -> ctx.getSource() == null ? 
                null : 
                modelService.getById(
                    ctx.getSource()
                );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converter).map(
                    src -> src.getModelPdiId(), 
                    Parameter::setModelPdi
                );
            }
        );
        Parameter parameter = modelMapper.map(
            dto, 
            Parameter.class
        );
        return parameter;
    }
}
