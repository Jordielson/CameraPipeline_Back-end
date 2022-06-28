package com.camerapipeline.camera_pipeline.mapper.pdi;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.dto.ParameterDTO;
import com.camerapipeline.camera_pipeline.dto.ValueParameterDTO;
import com.camerapipeline.camera_pipeline.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.model.Parameter;
import com.camerapipeline.camera_pipeline.model.ValueParameter;

@Component
public class ValueParameterMapper extends Mapper<ValueParameter, ValueParameterDTO> {
    @Autowired
    ParameterMapper parameterMapper;

    @Override
    public ValueParameterDTO toDTO(ValueParameter model) {
        TypeMap<ValueParameter, ValueParameterDTO> typeMap = getTypeMap(
            ValueParameter.class, 
            ValueParameterDTO.class
        );
        Converter<Parameter, ParameterDTO> converterListParameter =
            ctx -> ctx.getSource() == null ? 
                null : 
                parameterMapper.toDTO(
                    ctx.getSource()
                );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterListParameter)
                    .map(
                        ValueParameter::getParameter,
                        ValueParameterDTO::setParameter
                    );
            }
        );

        ValueParameterDTO dto = modelMapper.map(
            model, 
            ValueParameterDTO.class
        );
        return dto;
    }

    @Override
    public ValueParameter fromDTO(ValueParameterDTO dto) {
        TypeMap<ValueParameterDTO, ValueParameter> typeMap = getTypeMap(
            ValueParameterDTO.class, 
            ValueParameter.class
        );
        Converter<ParameterDTO, Parameter> converterListParameter =
            ctx -> ctx.getSource() == null ? 
                null : 
                parameterMapper.fromDTO(
                    ctx.getSource()
                );
        typeMap.addMappings(
            mapper -> {
                mapper.using(converterListParameter)
                    .map(
                        ValueParameterDTO::getParameter,
                        ValueParameter::setParameter
                    );
            }
        );

        ValueParameter value = modelMapper.map(
            dto, 
            ValueParameter.class
        );
        return value;
    }
}
